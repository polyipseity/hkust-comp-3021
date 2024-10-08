package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Folder implements Comparable<Folder> {
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
    if (!(obj instanceof Folder)) // if (obj == null || !getClass().equals(obj.getClass()))
      return false;
    Folder obj2 = (Folder) obj;
    if (!name.equals(obj2.name))
      return false;
    return true;
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
}
