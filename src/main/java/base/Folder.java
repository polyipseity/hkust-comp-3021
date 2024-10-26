package base;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Folder implements Comparable<Folder>, Serializable, Cloneable {
  @Serial
  private static final long serialVersionUID = 3854018765038654002L;

  private ArrayList<Note> notes;

  private String name;

  public Folder(String name) {
    this.notes = new ArrayList<>();
    this.name = name;
  }

  public void addNote(Note note) {
    notes.add(note);
  }

  public String getName() {
    return name;
  }

  public ArrayList<Note> getNotes() {
    return notes;
  }

  @Override
  public String toString() {
    int nText = 0, nImage = 0;

    for (Note note : notes) {
      if (note instanceof TextNote) {
        ++nText;
      } else if (note instanceof ImageNote) {
        ++nImage;
      }
    }

    return name + ':' + nText + ':' + nImage;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Folder obj2)) // if (obj == null || !getClass().equals(obj.getClass()))
      return false;
    return name.equals(obj2.name);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(new Object[] { name });
  }

  @Override
  public int compareTo(Folder o) {
    return name.compareTo(o.name);
  }

  public void sortNotes() {
    Collections.sort(notes);
  }

  public List<Note> searchNotes(String keywords) {
    List<List<String>> keywordTerms = new ArrayList<>();
    List<String> lastKeywordTerm = null;
    boolean sameTerm = false;
    for (String keyword : keywords.toLowerCase(Locale.ROOT).split(" +")) {
      if (keyword.isEmpty())
        continue;
      if ("or".equals(keyword)) {
        sameTerm = true;
        continue;
      }
      if (sameTerm) {
        if (lastKeywordTerm == null) {
          lastKeywordTerm = new ArrayList<>();
          keywordTerms.add(lastKeywordTerm);
        }
        lastKeywordTerm.add(keyword);
        sameTerm = false;
        continue;
      }
      lastKeywordTerm = new ArrayList<>();
      lastKeywordTerm.add(keyword);
      keywordTerms.add(lastKeywordTerm);
    }
    return keywordTerms.isEmpty() ? new ArrayList<>()
        : notes.stream()
            .filter(note -> keywordTerms.stream().allMatch(keywordTerm -> keywordTerm.stream().anyMatch(word -> {
              if (note.getTitle().toLowerCase(Locale.ROOT).contains(word))
                return true;
              if (note instanceof TextNote note2) {
                return note2.content.toLowerCase(Locale.ROOT).contains(word);
              }
              return false;
            }))).toList();
  }

  @Override
  public Folder clone() {
    try {
      Folder clone = (Folder) super.clone();
      clone.notes = new ArrayList<>(clone.notes.stream().map(note -> {
        if (note instanceof ImageNote note2) {
          return new ImageNote(note2);
        }
        if (note instanceof TextNote note2) {
          return new TextNote(note2);
        }
        return new Note(note);
      }).toList());
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
