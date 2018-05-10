package ru.tj.utils;

import ru.tj.models.NodeObject;

import java.io.InputStream;

public interface StaxParser {
    NodeObject parse (InputStream input);
}
