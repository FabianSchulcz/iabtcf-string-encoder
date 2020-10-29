package com.awin.tracking.tcf;

import com.iabtcf.encoder.PublisherRestrictionEntry;
import com.iabtcf.encoder.TCStringEncoder;
import com.iabtcf.utils.BitSetIntIterable;

import java.time.Instant;
import java.util.List;

public class StringGenerator {

    public static TCStringEncoder.Builder  generate(
            boolean isServiceSpecfific,
            int[] consentPurposes,
            int[] consentLegIntPurposes,
            int[] consentVendors,
            int[] consentVendorLegInt,
            List<PublisherRestrictionEntry> restrictions){

        TCStringEncoder.Builder tcStrBuilder = TCStringEncoder.newBuilder()
                .version(2)
                .created(Instant.now())
                .lastUpdated(Instant.now())
                .cmpId(842)
                .cmpVersion(2)
                .consentScreen(0)
                .consentLanguage("FR")
                .vendorListVersion(48)
                .tcfPolicyVersion(2)
                .isServiceSpecific(isServiceSpecfific)
                .useNonStandardStacks(false)
                .addPurposesConsent(BitSetIntIterable.from(consentPurposes))
                .addPurposesLITransparency(BitSetIntIterable.from(consentLegIntPurposes))
                .purposeOneTreatment(false)
                .publisherCC("DE")
                .addVendorConsent(BitSetIntIterable.from(consentVendors))
                .addVendorLegitimateInterest(BitSetIntIterable.from(consentVendorLegInt));

        if (restrictions != null)
        {
            for (PublisherRestrictionEntry restriction : restrictions) {
                tcStrBuilder.addPublisherRestrictionEntry(restriction);
            }
        }

        return tcStrBuilder;
    }
}
