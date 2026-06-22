package com.aistra.hail.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aistra.hail.app.AppManager
import com.aistra.hail.app.HailData

class FrozenWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        inputData.getString(HailData.KEY_PACKAGE)?.let { pkg ->
            val frozen = inputData.getBoolean(HailData.KEY_FROZEN, true)
            val appInfo = HailData.checkedList.find { it.packageName == pkg }
            val mode = appInfo?.let { HailData.getAppWorkingMode(it) } ?: HailData.workingMode
            AppManager.setAppFrozen(pkg, frozen, mode)
            return Result.success()
        }
        return Result.failure()
    }
}