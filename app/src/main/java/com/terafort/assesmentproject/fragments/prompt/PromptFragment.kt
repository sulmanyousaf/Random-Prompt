package com.terafort.assesmentproject.fragments.prompt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.terafort.assesmentproject.R
import com.terafort.assesmentproject.data.model.PromptList
import com.terafort.assesmentproject.data.repo.PromptRepository
import com.terafort.assesmentproject.databinding.FragmentPromptBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class PromptFragment : Fragment(R.layout.fragment_prompt) {

    private var _binding: FragmentPromptBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PromptViewModel>()
//    private val viewModel: PromptViewModel by viewModels {
//        PromptFragmentViewModelFactory(PromptRepository(requireContext()))
//    }
    private val promptList = mutableListOf<PromptList.Prompt>() // List to store prompts for later use

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPromptBinding.bind(view)

        binding.apply {
            btnInspired.setOnClickListener {
                // Use the promptList to set the TextInputEditText value
                if (promptList.isNotEmpty()) {
                    val randomIndex = (promptList.indices).random()
                    val randomPrompt = promptList[randomIndex]
                    tvPrompt.text = randomPrompt.title
                } else {
                    tvPrompt.text = ""
                    // Display a toast indicating that the list is empty
                    Toast.makeText(requireContext(), "The prompt list is empty", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.prompts.collect { prompts ->
                    promptList.clear()
                    if (prompts.isNotEmpty()) {
                        promptList.addAll(prompts)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}