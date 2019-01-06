package ua.com.training.model.tag;

import ua.com.training.model.ResourceEnum;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class DateTimeFormatTag extends SimpleTagSupport {
    private LocalDateTime localDateTime;
    private Locale locale;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws IOException {
        ResourceBundle LOCALE_PATTERNS = ResourceBundle.getBundle(ResourceEnum.LOCALE_PATTERNS.getBundleName(), locale);
        DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter
                .ofPattern(LOCALE_PATTERNS.getString("date.time.pattern"), locale);

        getJspContext().getOut().write(localDateTime.format(dateTimeFormatter));
    }
}
