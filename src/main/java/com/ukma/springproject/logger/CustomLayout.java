package com.ukma.springproject.logger;

import ch.qos.logback.classic.PatternLayout;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

@Plugin(name = "CustomLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
public class CustomLayout extends AbstractStringLayout {

    protected CustomLayout(Charset charset) {
        super(charset);
    }

    @Override
    public String toSerializable(LogEvent event) {
        StringBuilder throwable = new StringBuilder();
        if (event.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            event.getThrown().printStackTrace(pw);
            pw.close();
            throwable.append(sw);
        }
        StringBuilder retValue=new StringBuilder();
        retValue.append("<tr><td>")
                .append(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:S").format(event.getTimeMillis())).append(" ")
                .append(event.getLevel().toString()).append(" ").append(event.getLoggerName()).append(" ").append(event.getMessage().getFormattedMessage()).append(" ").append(throwable).append("</tr></td>\n");

        return retValue.toString();
    }

    @PluginFactory
    public static CustomLayout createLayout(@PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
        return new CustomLayout(charset);
    }
    @Override
    public byte[] getHeader() {
        return ("<html>\n  <body>\n" + "<Table>\n").getBytes();
    }
    @Override
    public byte[] getFooter() {
        return ("</table>\n</body>\n</html>").getBytes();
    }
}
