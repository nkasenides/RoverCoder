package io.github.nearchos.rovercoder.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.github.nearchos.rovercoder.ControlActivity;
import io.github.nearchos.rovercoder.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CodeControlFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ToggleButton toggleButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_code_control, container, false);

        toggleButton = root.findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> controlActivity.toggleLooper(isChecked));

        root.findViewById(R.id.downloadButton).setOnClickListener(v -> controlActivity.download());
        root.findViewById(R.id.resetButton).setOnClickListener(v -> controlActivity.reset());

        return root;
    }

    private ControlActivity controlActivity = null;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controlActivity = (ControlActivity) getActivity();
        assert controlActivity != null;
    }
}