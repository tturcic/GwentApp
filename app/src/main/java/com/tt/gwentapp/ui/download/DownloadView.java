package com.tt.gwentapp.ui.download;

import com.tt.gwentapp.ui.BaseView;

/**
 * @author tturcic
 *         \date 27.3.2017.
 */
public interface DownloadView extends BaseView {

    // Progress
    void showHideProgressView(boolean show);
    void showHideProgressIndicator(boolean show);
    void setProgressInfoText(int resId);

    // Error
    void showHideErrorView(boolean show);
    void setErrorText(int resId);

    void navigateToMain();
}
