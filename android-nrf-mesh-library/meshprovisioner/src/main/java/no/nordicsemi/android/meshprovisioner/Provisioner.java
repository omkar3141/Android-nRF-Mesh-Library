package no.nordicsemi.android.meshprovisioner;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import no.nordicsemi.android.meshprovisioner.transport.NetworkKey;
import no.nordicsemi.android.meshprovisioner.utils.MeshAddress;
import no.nordicsemi.android.meshprovisioner.utils.MeshTypeConverters;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Class definition of a Provisioner of mesh network
 */
@SuppressWarnings({"unused"})
@Entity(tableName = "provisioner",
        foreignKeys = @ForeignKey(entity = MeshNetwork.class,
                parentColumns = "mesh_uuid",
                childColumns = "mesh_uuid",
                onUpdate = CASCADE, onDelete = CASCADE),
        indices = @Index("mesh_uuid"))
@RestrictTo(RestrictTo.Scope.LIBRARY)
public class Provisioner {

    @ColumnInfo(name = "mesh_uuid")
    @NonNull
    @Expose
    private String meshUuid;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "provisioner_uuid")
    @Expose
    private String provisionerUuid;

    @ColumnInfo(name = "name")
    @Expose
    private String provisionerName = "nRF Mesh Provisioner";

    @TypeConverters(MeshTypeConverters.class)
    @Expose
    protected List<AllocatedGroupRange> allocatedGroupRanges = new ArrayList<>();

    @TypeConverters(MeshTypeConverters.class)
    @Expose
    protected List<AllocatedUnicastRange> allocatedUnicastRanges = new ArrayList<>();

    @TypeConverters(MeshTypeConverters.class)
    @Expose
    protected List<AllocatedSceneRange> allocatedSceneRanges = new ArrayList<>();

    @ColumnInfo(name = "sequence_number")
    @Expose
    private int sequenceNumber;

    @ColumnInfo(name = "provisioner_address")
    @Expose
    private int provisionerAddress = 0x7FFF;

    @ColumnInfo(name = "global_ttl")
    @Expose
    private int globalTtl = 5;

    @ColumnInfo(name = "last_selected")
    @Expose
    private boolean lastSelected;

    /**
     * Constructs {@link Provisioner}
     */
    public Provisioner(@NonNull final String provisionerUuid,
                       @NonNull final List<AllocatedUnicastRange> allocatedUnicastRanges,
                       @NonNull final List<AllocatedGroupRange> allocatedGroupRanges,
                       @NonNull final List<AllocatedSceneRange> allocatedSceneRanges,
                       @NonNull final String meshUuid) {
        this.provisionerUuid = provisionerUuid;
        this.allocatedUnicastRanges = allocatedUnicastRanges;
        this.allocatedGroupRanges = allocatedGroupRanges;
        this.allocatedSceneRanges = allocatedSceneRanges;
        this.meshUuid = meshUuid;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @Ignore
    public Provisioner() {

    }

    /**
     * Returns the provisionerUuid of the Mesh network
     *
     * @return String provisionerUuid
     */
    @NonNull
    public String getMeshUuid() {
        return meshUuid;
    }

    /**
     * Sets the provisionerUuid of the mesh network to this application key
     *
     * @param uuid mesh network provisionerUuid
     */
    public void setMeshUuid(@NonNull final String uuid) {
        meshUuid = uuid;
    }

    /**
     * Returns the provisioner name
     *
     * @return name
     */
    public String getProvisionerName() {
        return provisionerName;
    }

    /**
     * Sets a friendly name to a provisioner
     *
     * @param provisionerName friendly name
     */
    public void setProvisionerName(@NonNull final String provisionerName) throws IllegalArgumentException {
        if (TextUtils.isEmpty(provisionerName))
            throw new IllegalArgumentException("Name cannot be empty");
        this.provisionerName = provisionerName;
    }

    /**
     * Returns the provisionerUuid
     *
     * @return UUID
     */
    @NonNull
    public String getProvisionerUuid() {
        return provisionerUuid;
    }

    public void setProvisionerUuid(@NonNull final String provisionerUuid) {
        this.provisionerUuid = provisionerUuid;
    }

    /**
     * Returns {@link AllocatedGroupRange} for this provisioner
     *
     * @return allocated range of group addresses
     */
    public List<AllocatedGroupRange> getAllocatedGroupRanges() {
        return Collections.unmodifiableList(allocatedGroupRanges);
    }

    /**
     * Sets {@link AllocatedGroupRange} for this provisioner
     *
     * @param allocatedGroupRanges allocated range of group addresses
     */
    public void setAllocatedGroupRanges(final List<AllocatedGroupRange> allocatedGroupRanges) {
        this.allocatedGroupRanges = allocatedGroupRanges;
    }

    /**
     * Returns {@link AllocatedUnicastRange} for this provisioner
     *
     * @return allocated range of unicast addresses
     */
    public List<AllocatedUnicastRange> getAllocatedUnicastRanges() {
        return Collections.unmodifiableList(allocatedUnicastRanges);
    }

    /**
     * Sets {@link AllocatedGroupRange} for this provisioner
     *
     * @param allocatedUnicastRanges allocated range of unicast addresses
     */
    public void setAllocatedUnicastRanges(final List<AllocatedUnicastRange> allocatedUnicastRanges) {
        this.allocatedUnicastRanges = allocatedUnicastRanges;
    }

    /**
     * Returns {@link AllocatedSceneRange} for this provisioner
     *
     * @return allocated range of unicast addresses
     */
    public List<AllocatedSceneRange> getAllocatedSceneRanges() {
        return Collections.unmodifiableList(allocatedSceneRanges);
    }

    /**
     * Sets {@link AllocatedSceneRange} for this provisioner
     *
     * @param allocatedSceneRanges allocated range of unicast addresses
     */
    public void setAllocatedSceneRanges(final List<AllocatedSceneRange> allocatedSceneRanges) {
        this.allocatedSceneRanges = allocatedSceneRanges;
    }


    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(final int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getProvisionerAddress() {
        return provisionerAddress;
    }

    /**
     * Set provisioner address
     *
     * @param address address of the provisioner
     */
    public void setProvisionerAddress(final int address) {
        if(MeshAddress.isValidUnicastAddress(address))
        this.provisionerAddress = address;
    }

    public int getGlobalTtl() {
        return globalTtl;
    }

    public void setGlobalTtl(final int globalTtl) {
        this.globalTtl = globalTtl;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public boolean isLastSelected() {
        return lastSelected;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void setLastSelected(final boolean lastSelected) {
        this.lastSelected = lastSelected;
    }

    public int incrementSequenceNumber() {
        sequenceNumber = sequenceNumber + 1;
        return sequenceNumber;
    }
}
