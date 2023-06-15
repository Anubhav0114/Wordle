package com.example.wordle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.color
import com.example.wordle.databinding.ActivityMainBinding
import com.github.jinatonic.confetti.CommonConfetti
import com.github.jinatonic.confetti.ConfettiManager
import java.util.*

class MainActivity : AppCompatActivity() {
    private val  TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var guessNo = 0

        var target = FourLetterWordList.getRandomFourLetterWord()



        binding.submitBtn.setOnClickListener {
            var guess = binding.etGuess.text.toString().uppercase()
            Log.i(TAG , "the guess is $guess")
            Log.i(TAG , "the guess is $target")
            if (guess.length != 4 ) {
                Toast.makeText(this, "Guess is Invalid", Toast.LENGTH_LONG).show()
                binding.etGuess.text = null
                return@setOnClickListener
            } else {
                guessNo++
                checkGuess(guess, guessNo, target)
                binding.etGuess.text = null
            }
        }

        binding.resetBtn.setOnClickListener {
            binding.guess1.text = ""
            binding.guess2.text = ""
            binding.guess3.text = ""
            binding.guess4.text = ""

            binding.check1.text = ""
            binding.check2.text = ""
            binding.check3.text = ""
            binding.check4.text = ""

            binding.resetBtn.visibility = View.GONE
            binding.tvTarget.visibility = View.GONE
            binding.etGuess.visibility = View.VISIBLE
            binding.etGuess.text = null
            binding.submitBtn.visibility = View.VISIBLE

            guessNo = 0
            target = FourLetterWordList.getRandomFourLetterWord()

        }

    }

    private fun checkGuess(guess : String , guessNo : Int , target : String){

        when(guessNo){
            1 -> binding.guess1.text = guess
            2 -> binding.guess2.text = guess
            3 -> binding.guess3.text = guess
            4 -> binding.guess4.text = guess
        }

      //  IntArray(3) { Color.GREEN}

      //  val arr = arrayOf<Int>(Color.GREEN , Color.RED  , Color.BLUE)
        val s = SpannableStringBuilder()
        if (guess == target){
            CommonConfetti.rainingConfetti(binding.root ,  IntArray(1) {Color.GREEN})
                .infinite() .setEmissionDuration(6000)
            s.color(Color.GREEN) {append(guess)}
            finishGame(target)
        }else{
            for (i in 0..3){
                if (guess[i] == target[i]){
                    s.color(Color.GREEN) {append(guess[i])}
                }else if (guess[i] in target){
                    s.color(Color.BLUE) { append(guess[i])}
                }else{
                    s.color(Color.RED) { append(guess[i])}
                }
            }
        }

        when(guessNo){
            1 -> binding.check1.text = s
            2 -> binding.check2.text = s
            3 -> binding.check3.text = s
            4 -> {
                binding.check4.text = s
                finishGame(target)
            }
        }
    }
    private fun finishGame(target: String){
        binding.etGuess.visibility = View.GONE
        binding.submitBtn.visibility = View.GONE
        binding.tvTarget.text = target
        binding.tvTarget.visibility = View.VISIBLE
        binding.resetBtn.visibility = View.VISIBLE


    }
}

// author: calren
object FourLetterWordList {
    // List of most common 4 letter words from: https://7esl.com/4-letter-words/
    var fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    // Returns a list of four letter words as a list
    private fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }


    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }
}