package com.app.notes_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.app.notes_app.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    private  lateinit var db:NotesDatabaseHelper
    private  var noteId:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=NotesDatabaseHelper(this)

        noteId=intent.getIntExtra("noteId",1)

        if (noteId == -1) {
            finish()
            return
        }

        val note=db.getNoteById(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updatesaveButton.setOnClickListener{
            val newTitle=binding.updateTitleEditText.text.toString()
            val newContent=binding.updateContentEditText.text.toString()
            val updateNote=Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()

        }

    }
}