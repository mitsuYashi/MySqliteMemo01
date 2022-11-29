package com.example.mysqlitememo01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyInput: EditText = findViewById(R.id.EditKey)
        val editInput = findViewById<EditText>(R.id.EditText01)
        val readBtn = findViewById<Button>(R.id.KeyRead)
        val writeBtn = findViewById<Button>(R.id.KeyWrite)
        val clearBtn = findViewById<Button>(R.id.KeyClear)
        val listBtn = findViewById<Button>(R.id.KeyList)
        val deleteBtn = findViewById<Button>(R.id.KeyDelete)

        val db =AppDatabase.getInstance(this)

        val memopadDao = db.memopadDao()
        memopadDao.deleteAll()
        memopadDao.insert("テスト1", "なし")
        memopadDao.insert("テスト2", "なし")
        memopadDao.insert("テスト3", "なし")

        readBtn.setOnClickListener {
            val memos = memopadDao.findKey(keyInput.text.toString())
            editInput.setText("")
            if (memos.isEmpty()) {
                displayToast("指定したキーはありません")
                return@setOnClickListener
            }
            memos.forEach() {
                editInput.setText(editInput.text.toString() +
                        "KEY: " + it.key.toString() + "\n" +
                        "本文: " + it.memo.toString() + "\n" +
                        "作成日付: " + it.writeDate.toString() + "\n" +
                        "\n")
            }
            displayToast(keyInput.text.toString() + "の内容を読み込みました")
        }

        writeBtn.setOnClickListener {
            memopadDao.insert(keyInput.text.toString(), editInput.text.toString())
            displayToast(keyInput.text.toString() + "をキーに書き込みました")
        }

        clearBtn.setOnClickListener {
//            keyInput.setText("")
            editInput.setText("")
        }

        listBtn.setOnClickListener {
            val memos = memopadDao.getAll()
            editInput.setText("")
            memos.forEach() {
                editInput.setText(editInput.text.toString() +
                        "KEY: " + it.key.toString() + "\n" +
                        "本文: " + it.memo.toString() + "\n" +
                        "作成日付: " + it.writeDate.toString() + "\n" +
                        "\n")
            }
            displayToast("全レコードを読み込みました")
        }

        deleteBtn.setOnClickListener {
            val memos = memopadDao.findKey(keyInput.text.toString())
            if (memos.isEmpty()) {
                displayToast("指定したキーはありません")
            } else {
                memopadDao.delete(keyInput.text.toString())
                displayToast(keyInput.text.toString() + "をキーに削除しました")
            }
        }
    }

    fun displayToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}