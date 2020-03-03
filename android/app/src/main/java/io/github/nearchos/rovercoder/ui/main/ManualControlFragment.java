package io.github.nearchos.rovercoder.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.github.nearchos.rovercoder.ControlActivity;
import io.github.nearchos.rovercoder.R;
import robutev3.core.Interval;

/**
 * A placeholder fragment containing a simple view.
 */
public class ManualControlFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_manual_control, container, false);

        root.findViewById(R.id.forwardButton).setOnClickListener(v -> forward());
        root.findViewById(R.id.backwardButton).setOnClickListener(v -> backward());
        root.findViewById(R.id.turnLeftButton).setOnClickListener(v -> turnLeft());
        root.findViewById(R.id.turnRightButton).setOnClickListener(v -> turnRight());

        return root;
    }

    private ControlActivity controlActivity = null;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        controlActivity = (ControlActivity) getActivity();
        assert controlActivity != null;
    }

    final Interval motionInterval = Interval.milliseconds(250);

    private void forward() {
        controlActivity.brick().tank().forward(motionInterval).go();
    }

    private void backward() {
        controlActivity.brick().tank().backward(motionInterval).go();
    }

    private void turnLeft() {
        controlActivity.brick().tank().turnLeft().go();
    }

    private void turnRight() {
        controlActivity.brick().tank().turnRight().go();
    }
}