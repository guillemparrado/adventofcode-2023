package com.adventofcode.utils;


import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LineReader {

  private @NotNull final String filePath;


  public LineReader(
    final @NotNull String filePath
  ){
    this.filePath = filePath;
  }

  public Stream<String> getLines() {
    try {
      return Files.lines(Paths.get(this.filePath));
    }
    catch (Exception e){
      System.out.println("Error: el archivo no existe");
      return null;
    }
  }
}
