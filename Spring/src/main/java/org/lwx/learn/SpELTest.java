package org.lwx.learn;

import lombok.Data;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SpELTest {
    public static void main(String[] args) {
//        ExpressionParser parser = new SpelExpressionParser();
//        Expression expression = parser.parseExpression("'Hello World'.concat('!').bytes.length");
//        String expressionValue = expression.getValue(Integer.class).toString();
//        System.out.println(expressionValue);
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(2025, Calendar.AUGUST, 10);

        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression("name"); // Parse name as an expression
        String name = (String) exp.getValue(tesla);
        System.out.println(name);
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = Boolean.TRUE.equals(exp.getValue(tesla, Boolean.class));
        System.out.println(result);
    }
    @Data
    static class Inventor {
        private String name;
        private Date birthday;
        private String nationality;

        public Inventor(String name, Date birthday, String nationality) {
            this.name = name;
            this.birthday = birthday;
            this.nationality = nationality;
        }
    }
}
