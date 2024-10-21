package base;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable {
  @Serial
  private static final long serialVersionUID = 2982743321718011092L;

  private ArrayList<Folder> folders;

  public NoteBook() {
    this.folders = new ArrayList<>();
  }

  public boolean createTextNote(String folderName, String title) {
    return insertNote(folderName, new TextNote(title));
  }

  public boolean createTextNote(String folderName, String title, String content) {
    return insertNote(folderName, new TextNote(title, content));
  }

  public boolean createImageNote(String folderName, String title) {
    return insertNote(folderName, new ImageNote(title));
  }

  public boolean insertNote(String folderName, Note note) {
    Folder folder = null;
    for (Folder folder2 : folders) {
      if (folder2.getName().equals(folderName)) {
        folder = folder2;
        break;
      }
    }
    if (folder == null) {
      this.folders.add(folder = new Folder(folderName));
    }

    for (Note note2 : folder.getNotes()) {
      if (note2.equals(note)) {
        System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed.");
        return false;
      }
    }
    folder.addNote(note);
    return true;
  }

  public ArrayList<Folder> getFolders() {
    return folders;
  }

  public void sortFolders() {
    for (Folder folder : folders) {
      folder.sortNotes();
    }
    Collections.sort(folders);
  }

  public List<Note> searchNotes(String keywords) {
    return folders.stream().flatMap(folder -> folder.searchNotes(keywords).stream()).toList();
  }

  public boolean createNote(String folderName, String title) {
    return createImageNote(folderName, title);
  }

  public boolean createNote(String folderName, String title, String content) {
    return createTextNote(folderName, title, content);
  }
}
