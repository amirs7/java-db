package xyz.softeng.dbscript;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {
    private static final AttributedStyle STYLE = AttributedStyle.DEFAULT
            .bold()
            .foreground(AttributedStyle.BLUE);

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("myshell>", STYLE);
    }
}
