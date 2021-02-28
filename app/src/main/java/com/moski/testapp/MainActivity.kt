package com.moski.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import java.io.Serializable

class Buveur: Serializable {
    var name = ""
    var glouglouNb = 0
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //anti-barre de merde
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Player_List: Array<Buveur> = emptyArray()
        var arrayOfEditTexts: Array<EditText> = emptyArray()



        val ButtonPlus = findViewById<Button>(R.id.buttonPlus)
        val ButtonFinish = findViewById<Button>(R.id.buttonFinish)
        var plrLayout = findViewById<LinearLayout>(R.id.player_layout)

        for (x in 0..1) arrayOfEditTexts += createPlayer("Nom N°"+(arrayOfEditTexts.size+1), plrLayout)

        ButtonPlus.setOnClickListener {
            arrayOfEditTexts += createPlayer("Nom N°"+(arrayOfEditTexts.size+1), plrLayout)
        }

        ButtonFinish.setOnClickListener{
            Player_List = initPlayer_List(arrayOfEditTexts)
            load_Game(Player_List)
        }
    }

    private fun load_Game(player_list: Array<Buveur>){
        val monIntent : Intent =  Intent(this,Main2Activity::class.java)
        monIntent.putExtra("player", player_list);
        startActivity(monIntent)
    }

    private fun initPlayer_List(Plr_List: Array<EditText>): Array<Buveur> {
        var returnPlayerList: Array<Buveur> = emptyArray()
        for (player in Plr_List)
        {
            if(player.text.toString() != "")
            {
                val current_player = Buveur()
                current_player.name = player.text.toString()
                returnPlayerList += current_player;
            }
        }
        return returnPlayerList
    }

    private fun createButton(text: String): Button {

        val constraintLayout = findViewById<ConstraintLayout>(R.id.gameBackground) as ConstraintLayout
        val newButton = Button(this)
        newButton.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        newButton.text = text
        constraintLayout.addView(newButton);

        return newButton
    }

    private fun format_entry(entry: EditText){
        entry.textSize = 24.0f
        entry.width = 250
        return
    }

    private fun createPlayer(hint: String, layout: LinearLayout): EditText {
        val newPlayer = EditText(this)
        newPlayer.hint = hint
        layout.addView(newPlayer)
        format_entry(newPlayer)

        return newPlayer
    }

}
