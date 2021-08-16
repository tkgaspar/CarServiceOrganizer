package com.ownproject.ServiceOrganizer.Services;

import com.ownproject.ServiceOrganizer.Mapper.NoteMapper;
import com.ownproject.ServiceOrganizer.Model.Note;
import com.ownproject.ServiceOrganizer.Model.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotesList(Integer userId) {
        return this.noteMapper.getAllNotes(userId);
    }


    public Integer addNote(NoteForm noteform, Integer userId) {
        Note note = new Note();
        note.setNoteTitle(noteform.getNoteTitle());
        note.setNoteDescription(noteform.getNoteDescription());
        note.setUserId(userId);
        return this.noteMapper.insert(note);
    }

    public Integer deleteNote(String noteTitle, Integer userid) {
      return  this.noteMapper.delete(noteTitle, userid);
    }

    public Note getNote (String noteTitle,Integer userId){
        return this.noteMapper.getNote(noteTitle, userId);
    }

    public void updateNote(NoteForm noteForm){
        this.noteMapper.updateNote(noteForm.getNoteTitle(),noteForm.getNoteDescription(), noteForm.getNoteId());
    }


}
