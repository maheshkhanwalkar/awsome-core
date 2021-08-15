package com.revtekk.awsome.core.region;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegionStoreTest
{
    @Test
    public void testDefaultRetrieve() {
        Region def = RegionStore.getRegion();
        assertEquals(def, Region.US_EAST_1);
    }

    @Test
    public void testChange() {
        Region def = RegionStore.getRegion();
        assertEquals(def, Region.US_EAST_1);

        RegionStore.setRegion(Region.AP_SOUTHEAST_2);
        def = RegionStore.getRegion();
        assertEquals(def, Region.AP_SOUTHEAST_2);

        // Reset back to original state -- so other tests don't fail
        RegionStore.setRegion(Region.US_EAST_1);
    }

    @Test
    public void testThreadLocality() throws InterruptedException {
        RegionStore.setRegion(Region.EU_WEST_1);

        Thread t = new Thread(() -> {
            Region def = RegionStore.getRegion();
            assertEquals(def, Region.US_EAST_1);

            RegionStore.setRegion(Region.AP_EAST_1);
            def = RegionStore.getRegion();
            assertEquals(def, Region.AP_EAST_1);
        });

        t.start();
        t.join();

        Region def = RegionStore.getRegion();
        assertEquals(def, Region.EU_WEST_1);

        // Reset back to original state -- so other tests don't fail
        RegionStore.setRegion(Region.US_EAST_1);
    }
}
