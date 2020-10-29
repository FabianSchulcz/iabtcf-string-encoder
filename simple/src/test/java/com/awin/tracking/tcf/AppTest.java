package com.awin.tracking.tcf;

import static org.junit.Assert.*;

import com.iabtcf.decoder.TCString;
import com.iabtcf.encoder.PublisherRestrictionEntry;
import com.iabtcf.encoder.TCStringEncoder;
import com.iabtcf.utils.BitSetIntIterable;
import com.iabtcf.v2.RestrictionType;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    PublisherRestrictionEntry legIntPurpose7_NotAllowed = PublisherRestrictionEntry.newBuilder()
            .addVendor(BitSetIntIterable.from(467))
            .restrictionType(RestrictionType.NOT_ALLOWED)
            .purposeId(7)
            .build();

    PublisherRestrictionEntry legIntPurpose7_RequireConsent = PublisherRestrictionEntry.newBuilder()
            .addVendor(BitSetIntIterable.from(467))
            .restrictionType(RestrictionType.REQUIRE_CONSENT)
            .purposeId(7)
            .build();

    PublisherRestrictionEntry legIntPurpose7_RequireLegInt = PublisherRestrictionEntry.newBuilder()
            .addVendor(BitSetIntIterable.from(467))
            .restrictionType(RestrictionType.REQUIRE_LEGITIMATE_INTEREST)
            .purposeId(7)
            .build();

    // decode on web page fails if this restriction is used -> cannot create hash  .. invalid publisher restriction
    PublisherRestrictionEntry legIntPurpose7_Undefined = PublisherRestrictionEntry.newBuilder()
            .addVendor(BitSetIntIterable.from(467))
            .restrictionType(RestrictionType.UNDEFINED)
            .purposeId(7)
            .build();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void generateAllTrueString()
    {
        TCStringEncoder.Builder  tcStrBuilder = StringGenerator.generate(true,
                new int[]{1},
                new int[]{7},
                new int[]{467},
                new int[]{467},
                new ArrayList<PublisherRestrictionEntry>() {{ add(legIntPurpose7_NotAllowed); }}
                );

        TCString tcStr = tcStrBuilder.toTCString();
        String tcStrEncoded = tcStrBuilder.encode();
        System.out.println(tcStrEncoded  + " ----- " + tcStr);
        assertEquals(tcStr, TCString.decode(tcStrEncoded));

    }
}
