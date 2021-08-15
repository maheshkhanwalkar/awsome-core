package com.revtekk.awsome.core.region;

import software.amazon.awssdk.regions.Region;

/**
 * Region-related store.
 *
 * This class implements logic to store the current requested region, which is
 * referenced throughout the library.
 *
 * The main purpose is to abstract away the region details to this "container", so
 * each service-related class does not need to maintain default and region-provided logic.
 *
 * Instead, all classes will use the RegionStore to get the currently set region and
 * build their internal clients accordingly. If there ever arises a need to change the region,
 * it can be updated, though the visibility is restricted to the thread-level. This allows
 * multi-threaded applications to work with different regions (if so desired) without things
 * breaking in strange ways.
 */
public final class RegionStore
{
    private static final ThreadLocal<Region> region = ThreadLocal.withInitial(() -> {
        String regionStr = System.getenv("AWS_REGION");

        if(regionStr == null) {
            return Region.US_EAST_1;
        } else {
            return Region.of(regionStr);
        }
    });

    public static void setRegion(Region region) {
        RegionStore.region.set(region);
    }

    public static Region getRegion() {
        return region.get();
    }
}
