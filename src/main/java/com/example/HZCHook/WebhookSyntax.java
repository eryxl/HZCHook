package com.example.hzchook;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimpleExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * WebhookSyntax - Handles the webhook-related expressions for the HZCHook plugin
 * 
 * Syntaxes supported:
 *   - webhook %string%
 *   - webhook %string% with data %object%
 *   - send webhook %string% with data %object%
 */
public class WebhookSyntax extends SimpleExpression<String> {
    private Expression<String> url;
    private Expression<Object> data;

    /**
     * Registers the webhook syntax with the HZCHook plugin
     * @param plugin The HZCHook instance
     */
    public static void register(HZCHook plugin) {
        plugin.registerExpression(
            WebhookSyntax.class,
            String.class,
            "[skript] webhook",
            "[skript] webhook %string%",
            "[skript] webhook %string% with data %object%",
            "send [skript] webhook %string% with data %object%"
        );
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        url = (Expression<String>) exprs[0];
        data = (Expression<Object>) exprs[1];
        return true;
    }

    @Override
    protected String[] get(Event e) {
        WebhookExecutor executor = new WebhookExecutor();
        String webhookUrl = url.getSingle(e);
        Object dataObj = data.getSingle(e);
        
        executor.sendWebhook(webhookUrl, dataObj);
        return new String[]{};
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "send webhook " + url.toString(e, debug) + " with data " + data.toString(e, debug);
    }
}
