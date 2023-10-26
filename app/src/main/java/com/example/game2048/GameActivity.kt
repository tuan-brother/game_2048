package com.example.game2048

import android.app.AlertDialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.game2048.databinding.ActivityGameBinding
import com.example.game2048.databinding.DialogEnterNicknameBinding

/**
 * Created by shen on 17/4/11.
 */

class GameActivity : AppCompatActivity() {

    val PREF_BEST_SCORE = "best_score"
    val PREF_NICK_NAME = "nick_name"

    var mBestScore = 0
    lateinit var gameController: GameController
    lateinit var binding: ActivityGameBinding

    //    lateinit var mServerUtil: ServerUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameController = GameController(applicationContext)
        binding.gameView.gameConfig = gameController.getDefaultGameConfig()
        binding.gameView.start()

        binding.start.setOnClickListener {
            binding.gameView.start()
        }

        mBestScore = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            .getInt(PREF_BEST_SCORE, 0)
        binding.bestScore.text = "" + mBestScore

        var nickName = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            .getString(PREF_NICK_NAME, null)

        if (!TextUtils.isEmpty(nickName)) {
//            mServerUtil.init(nickName, object : ValueEventListener {
//                override fun onDataChange(var1: DataSnapshot) {
//                    var score = var1.getValue(Int::class.java)
//                    Log.d("syh", "score:" + score + "  mBestScore:" + mBestScore)
//                }
//
//                override fun onCancelled(var1: DatabaseError) {
//
//                }
//            })
        }

    }

    fun onScoreChanged(scoreValue: Int) {
        binding.score.text = scoreValue.toString()
    }

    override fun onPause() {
        super.onPause()
    }

    fun onGameComplete() {
        var score = Integer.parseInt(binding.score.text.toString())
        if (score > mBestScore) {
            mBestScore = score
            binding.bestScore.text = "" + score

            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit()
                .putInt(PREF_BEST_SCORE, score).apply()
        }

        if (TextUtils.isEmpty(
                PreferenceManager.getDefaultSharedPreferences(applicationContext)
                    .getString(PREF_NICK_NAME, null)
            )
        ) {
            enterNickName()
        }
    }

    fun enterNickName() {
        val binding: DialogEnterNicknameBinding =
            DialogEnterNicknameBinding.inflate(LayoutInflater.from(this), null, false)
        var dialog = AlertDialog.Builder(this).setTitle(R.string.tips).setView(binding.root)
            .setPositiveButton(
                R.string.dialog_confirm
            ) { dialog, which -> onEnterNickName(binding.nickName.text.toString().trim()) }
            .setNegativeButton(R.string.dialog_cancel) { dialog, which -> }.create()
        dialog.show()
    }

    fun onEnterNickName(nickName: String) {
        if (nickName.isEmpty()) {
            enterNickName()
            return
        }
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit()
            .putString(PREF_NICK_NAME, nickName).apply()


    }

}