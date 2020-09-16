package com.musalaSoft.weather.ui.splash

import android.graphics.Color
import android.location.Location
import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.musalaSoft.weather.core.BaseFragment
import com.musalaSoft.weather.databinding.FragmentSplashBinding
import com.musalaSoft.weather.di.Injectable
import com.mikhaellopez.rxanimation.*
import io.reactivex.disposables.CompositeDisposable
import com.musalaSoft.weather.R
import com.musalaSoft.weather.db.entity.CoordEntity
import com.musalaSoft.weather.utils.LocationHelper

class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(R.layout.fragment_splash, SplashFragmentViewModel::class.java),
    Injectable {

    private var disposable = CompositeDisposable()

    override fun init() {
        super.init()
        binding.viewModel?.navigateDashboard?.let { startSplashAnimation(it) }
        Handler().postDelayed({
            binding.viewModel?.navigateDashboard?.let { endSplashAnimation()

                LocationHelper().startListeningUserLocation(binding.root.context , object : LocationHelper.LocationListener {
                    override fun onLocationEnabled(location: Location) {
                        binding.viewModel?.saveCoordsToSharedPref(CoordEntity(location.latitude, location.longitude))
                    }
                })
            }
        }, 4170)

    }

    private fun startSplashAnimation(navigateToDashboard: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    binding.imageViewBottomDrawable.translationY(500f),
                    binding.imageViewEllipse.fadeOut(0L),
                    binding.imageViewBottomDrawable.fadeOut(0L),
                    binding.imageViewBigCloud.translationX(-500F, 0L),
                    binding.imageViewSmallCloud.translationX(500f, 0L),
                    binding.imageViewBottomDrawableShadow.translationY(500f),
                    binding.imageViewMainCloud.fadeOut(0L),
                    binding.imageViewBottomDrawableShadow.fadeOut(0L)
                ),

                RxAnimation.together(
                    binding.imageViewBottomDrawable.fadeIn(1000L),
                    binding.imageViewBottomDrawable.translationY(-1f),
                    binding.imageViewBottomDrawableShadow.fadeIn(1250L),
                    binding.imageViewBottomDrawableShadow.translationY(-1f)
                ),

                RxAnimation.together(
                    binding.imageViewEllipse.fadeIn(1000L),
                    binding.imageViewEllipse.translationY(-50F, 1000L)
                ),

                RxAnimation.together(
                    binding.imageViewBigCloud.translationX(-15f, 1000L),
                    binding.imageViewSmallCloud.translationX(25f, 1000L)
                ),

                binding.imageViewMainCloud.fadeIn(500L),
            ).doOnTerminate {
                findNavController().graph.startDestination = R.id.dashboardFragment // Little bit tricky solution :)
                if (navigateToDashboard)
                    endSplashAnimation()
            }
                .subscribe()
        )
    }

    private fun endSplashAnimation() {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    binding.imageViewBottomDrawable.fadeOut(300L),
                    binding.imageViewBottomDrawable.translationY(100f),
                    binding.imageViewBottomDrawableShadow.fadeOut(300L),
                    binding.imageViewBottomDrawableShadow.translationY(100f)
                ),

                RxAnimation.together(
                    binding.imageViewEllipse.fadeOut(300L),
                    binding.imageViewEllipse.translationY(500F, 300L)
                ),

                RxAnimation.together(
                    binding.imageViewBigCloud.translationX(500f, 300L),
                    binding.imageViewSmallCloud.translationX(-500f, 300L)
                ),

                binding.imageViewMainCloud.fadeOut(300L),
                binding.rootView.backgroundColor(
                    Color.parseColor("#5D50FE"),
                    Color.parseColor("#FFFFFF"),
                    duration = 750L
                )
            )
                .doOnTerminate {
                    findNavController().graph.startDestination = R.id.dashboardFragment
                    navigate(R.id.action_splashFragment_to_dashboardFragment)
                }
                .subscribe()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}
