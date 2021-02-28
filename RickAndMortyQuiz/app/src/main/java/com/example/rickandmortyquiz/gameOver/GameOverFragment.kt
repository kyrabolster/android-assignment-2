package com.example.rickandmortyquiz.gameOver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyquiz.R
import com.example.rickandmortyquiz.databinding.GameOverFragmentBinding
import com.example.rickandmortyquiz.game.GameViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameOverFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameOverFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: GameOverFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_over_fragment, container, false)

        val args = GameOverFragmentArgs.fromBundle(requireArguments())

        binding.finalScoreText.text = args.score

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}