package com.duy.mycontact.presentation.login

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.duy.mycontact.MainActivity
import com.duy.mycontact.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val TAG: String = LoginFragment::class.java.simpleName
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.login)

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                login.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    username.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    password.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loading.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }
        }
        username.addTextChangedListener(afterTextChangedListener)
        password.addTextChangedListener(afterTextChangedListener)
        password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    username.text.toString(),
                    password.text.toString()
                )
            }
            false
        }

        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginViewModel.login(
                username.text.toString(),
                password.text.toString()
            )
        }

        img_view_password.setOnClickListener {
            val bmap = (img_view_password.drawable.current as BitmapDrawable).bitmap

            val imgShowPassWord = resources.getDrawable(R.drawable.pw_view_blue)
            val showPassWord = (imgShowPassWord as BitmapDrawable).bitmap

            if (bmap.sameAs(showPassWord)) {
                img_view_password.setImageResource(R.drawable.pw_hide_blue)
                password.transformationMethod = null
            } else {
                img_view_password.setImageResource(R.drawable.pw_view_blue)
                password.transformationMethod = PasswordTransformationMethod()
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val direction = LoginFragmentDirections.actionLoginFragmentToContactListFragment(
            model.userId,
            model.displayName
        )
        findNavController().navigate(direction)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}