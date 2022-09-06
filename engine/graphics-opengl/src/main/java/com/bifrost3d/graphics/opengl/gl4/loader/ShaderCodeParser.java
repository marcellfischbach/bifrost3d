package com.bifrost3d.graphics.opengl.gl4.loader;

import com.bifrost3d.core.resource.AssetManager;
import com.bifrost3d.core.resource.ResourceLocator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShaderCodeParser {

    private String source;

    private List<String> lines;

    private Set<String> includedIncludes = new HashSet<>();

    public ShaderCodeParser(String source) {
        this.source = source;
        this.lines = toLines(source);
    }

    private void doParse() {
        replaceIncludes();

        mergeLinesToSource();
    }

    private void replaceIncludes() {
        int line = 0;
        while (line < lines.size()) {
            List<String> updateLines = replaceLine(lines.get(line));
            int idx = line;
            lines.remove(idx);
            for (String updateLine : updateLines) {
                lines.add(idx++, updateLine);
            }
            line++;
        }
    }

    private final Pattern pattern = Pattern.compile("(#include<)(.*)(>)");

    private List<String> replaceLine(String line) {
        line = line.trim();

        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            return Arrays.asList(line);
        }

        String include = matcher.group(2);
        if (this.includedIncludes.contains(include)) {
            return Arrays.asList("// ignore " + line);
        }
        this.includedIncludes.add(include);

        Optional<ShaderSource> shaderSource = AssetManager.instance().load(ShaderSource.class, new ResourceLocator(include));

        ArrayList<String> newLines = new ArrayList<>();
        newLines.add("// begin: " + line);
        if (shaderSource.isPresent()) {
            newLines.addAll(toLines(shaderSource.get()));
        }
        newLines.add("// end: " + line);
        return newLines;
    }




    private void mergeLinesToSource() {
        StringBuilder sb = new StringBuilder();
        for (String line : this.lines) {
            sb.append(line).append("\n");
        }
        this.source = sb.toString();
    }

    private static List<String> toLines(ShaderSource shaderSource) {
        return toLines(shaderSource.getSource());
    }

    private static List<String> toLines(String shaderSource) {
        String[] lines = lines(shaderSource);
        return new ArrayList<>(Arrays.asList(lines));
    }


    private static String[] lines(String source) {
        source = source.replace("\\r\\n", "\n");
        return source.split("\\n");
    }

    public static String parse(String source) {
        ShaderCodeParser parse = new ShaderCodeParser(source);
        parse.doParse();
        return parse.source;
    }


}
