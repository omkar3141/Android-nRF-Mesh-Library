package no.nordicsemi.android.nrfmeshprovisioner.viewmodels;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import no.nordicsemi.android.nrfmeshprovisioner.keys.AppKeysActivity;

/**
 * ViewModel for {@link AppKeysActivity}
 */
public class EditProvisionerViewModel extends BaseViewModel {

    @Inject
    EditProvisionerViewModel(@NonNull final NrfMeshRepository nrfMeshRepository) {
        super(nrfMeshRepository);
    }
}
