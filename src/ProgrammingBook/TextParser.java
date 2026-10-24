package ProgrammingBook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public static String normalizeSpaces(String text) {
        return text.replaceAll("[\\t ]+", " ").trim();
    }

    public static Text parse(String rawText) {
        String text = normalizeSpaces(rawText);
        Text result = new Text();

        String[] parts = text.split("```");
        for (int i = 0; i < parts.length; i++) {
            if (i % 2 == 1) {
                String code = parts[i].trim();
                code = code.substring(code.indexOf('\n') + 1);
                result.addListing(new CodeListing(code));
            } else {
//                result.addParagraph(parseParagraph(parts[i]));
                for (String block : parts[i].split("\\n\\s*\\n")) {
                    if (!block.isBlank()) {
                        result.addParagraph(parseParagraph(block));
                    }
                }
            }
        }
        return result;
    }

    private static Paragraph parseParagraph(String text) {
        Paragraph paragraph = new Paragraph();
        String[] parts = text.split("(?<=[.!?])\\s+");
        for (String part : parts) {
            if (part.isBlank()) continue;
            paragraph.addSentence(parseSentence(part));
        }
        return paragraph;
    }

    private static Sentence parseSentence(String str) {
        Sentence sentence = new Sentence();
//        String[] tokens = str.split("(?<=\\w)(?=\\p{Punct})|\\s+");
        Matcher m = Pattern.compile("[\\p{L}\\p{N}_]+|\\p{Punct}").matcher(str);
        while (m.find()) {
            String token = m.group();
            char first = token.charAt(0);
            if (Character.isLetterOrDigit(first) || first == '_') {
                sentence.addToken(new Word(token));
            } else {
                sentence.addToken(new Punctuation(token));
            }
        }
        return sentence;
    }
}