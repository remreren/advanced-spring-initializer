package com.remreren.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Keyword {
    public static final Keyword PUBLIC = new Keyword("public", Group.ACCESS_MODIFIER);
    public static final Keyword PRIVATE = new Keyword("private", Group.ACCESS_MODIFIER);
    public static final Keyword PROTECTED = new Keyword("protected", Group.ACCESS_MODIFIER);
    public static final Keyword STATIC = new Keyword("static", Group.NON_ACCESS_MODIFIER) {
        @Override
        public int hashCode() {
            return 1;
        }
    };
    public static final Keyword FINAL = new Keyword("final", Group.NON_ACCESS_MODIFIER) {
        @Override
        public int hashCode() {
            return 2;
        }
    };
    public static final Keyword VOID = new Keyword("void", Group.NON_ACCESS_MODIFIER) {
        @Override
        public int hashCode() {
            return 3;
        }
    };

    public enum Group {
        ACCESS_MODIFIER,
        NON_ACCESS_MODIFIER,
    }

    private final String value;

    private final Group group;

    @Override
    public int hashCode() {
        return 0;
    }
}

