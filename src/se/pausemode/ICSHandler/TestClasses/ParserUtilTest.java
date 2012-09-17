package se.pausemode.ICSHandler.TestClasses;

import junit.framework.TestCase;
import se.pausemode.ICSHandler.DataTypes.RelatedToData;
import se.pausemode.ICSHandler.DataTypes.RequestStatusData;
import se.pausemode.ICSHandler.Util.ParserUtil;

/**
 * Created with IntelliJ IDEA.
 * User: mattiaszi
 * Date: 2012-09-14
 * Time: 15:23
 * To change this template use File | Settings | File Templates.
 */
public class ParserUtilTest extends TestCase {
    public void testParseRelatedToData() throws Exception {
        RelatedToData rd;
        assertNull(ParserUtil.parseRelatedToData(null));

        rd = ParserUtil.parseRelatedToData("jsmith.part7.19960817T083000.xyzMail@example.com");
        assertNotNull(rd);
        assertNull(rd.getRELTYPE());
        assertEquals(rd.getVALUE(),"jsmith.part7.19960817T083000.xyzMail@example.com");

        rd = ParserUtil.parseRelatedToData("19960401-080045-4000F192713-0052@example.com");
        assertNotNull(rd);
        assertNull(rd.getRELTYPE());
        assertEquals(rd.getVALUE(),"19960401-080045-4000F192713-0052@example.com");

        rd = ParserUtil.parseRelatedToData("RELTYPE=PARENT:jsmith.part7.19960817T083000.xyzMail@example.com");
        assertNotNull(rd);
        assertEquals(RelatedToData.RELTYPE_TYPE.PARENT,rd.getRELTYPE());
        assertEquals(rd.getVALUE(),"jsmith.part7.19960817T083000.xyzMail@example.com");

        rd = ParserUtil.parseRelatedToData("RELTYPE=CHILD:jsmith.part7.19960817T083000.xyzMail@example.com");
        assertNotNull(rd);
        assertEquals(RelatedToData.RELTYPE_TYPE.CHILD,rd.getRELTYPE());
        assertEquals(rd.getVALUE(),"jsmith.part7.19960817T083000.xyzMail@example.com");

        rd = ParserUtil.parseRelatedToData("RELTYPE=SIBLING:jsmith.part7.19960817T083000.xyzMail@example.com");
        assertNotNull(rd);
        assertEquals(RelatedToData.RELTYPE_TYPE.SIBLING,rd.getRELTYPE());
        assertEquals(rd.getVALUE(),"jsmith.part7.19960817T083000.xyzMail@example.com");

        rd = ParserUtil.parseRelatedToData("RELTYPE=NON_DEFINED:jsmith.part7.19960817T083000.xyzMail@example.com");
        assertNotNull(rd);
        assertEquals(RelatedToData.RELTYPE_TYPE.OTHER,rd.getRELTYPE());
        assertEquals(rd.getVALUE(),"jsmith.part7.19960817T083000.xyzMail@example.com");

        rd = ParserUtil.parseRelatedToData("RELTYPE=SIBLING;SOMETHINGELSE=BANANA:jsmith.part7.19960817T083000.xyzMail@example.com");
        assertNotNull(rd);
        assertEquals(RelatedToData.RELTYPE_TYPE.SIBLING,rd.getRELTYPE());
        assertEquals(rd.getVALUE(),"jsmith.part7.19960817T083000.xyzMail@example.com");

    }

    public void testParseRequestStatusData() throws Exception {
        RequestStatusData rsd = null;
        assertNull(ParserUtil.parseRequestStatusData(null));

        rsd = ParserUtil.parseRequestStatusData("2.0;Success");
        assertNotNull(rsd);
        assertNull(rsd.getLanguage());
        assertEquals("2.0", rsd.getStatCode());
        assertEquals("Success", rsd.getStatDesc());
        assertNull(rsd.getExtData());

        rsd = ParserUtil.parseRequestStatusData("3.1;Invalid property value;DTSTART:96-Apr-01");
        assertNotNull(rsd);
        assertNull(rsd.getLanguage());
        assertEquals("3.1", rsd.getStatCode());
        assertEquals("Invalid property value", rsd.getStatDesc());
        assertEquals("DTSTART:96-Apr-01", rsd.getExtData());

        rsd = ParserUtil.parseRequestStatusData("2.8; Success\\, repeating event ignored. Scheduled as a single event.;RRULE:FREQ=WEEKLY\\;INTERVAL=2");
        assertNotNull(rsd);
        assertNull(rsd.getLanguage());
        assertEquals("2.8", rsd.getStatCode());
        assertEquals(" Success\\, repeating event ignored. Scheduled as a single event.", rsd.getStatDesc());
        assertEquals("RRULE:FREQ=WEEKLY\\;INTERVAL=2", rsd.getExtData());

        rsd = ParserUtil.parseRequestStatusData("3.7;Invalid calendar user;ATTENDEE:mailto:jsmith@example.com");
        assertNotNull(rsd);
        assertNull(rsd.getLanguage());
        assertEquals("3.7", rsd.getStatCode());
        assertEquals("Invalid calendar user", rsd.getStatDesc());
        assertEquals("ATTENDEE:mailto:jsmith@example.com", rsd.getExtData());

        rsd = ParserUtil.parseRequestStatusData("LANGUAGE=en-US;FAVOURITECHEESE=Saint Agur:3.1.2;Invalid property value;DTSTART:96-Apr-01");
        assertNotNull(rsd);
        assertEquals("en-US", rsd.getLanguage());
        assertEquals("3.1.2", rsd.getStatCode());
        assertEquals("Invalid property value", rsd.getStatDesc());
        assertEquals("DTSTART:96-Apr-01", rsd.getExtData());
    }

    public void testParseCategoriesData() throws Exception {

    }

    public void testParseAttendeeData() throws Exception {

    }

    public void testParsePeopleData() throws Exception {

    }

    public void testParseAttachData() throws Exception {

    }

    public void testParseDurationData() throws Exception {

    }

    public void testParseRecurrenceRule() throws Exception {

    }

    public void testParseWeekDay() throws Exception {

    }

    public void testParseStringData() throws Exception {

    }

    public void testParseDateData() throws Exception {

    }

    public void testParseRecurrenceID() throws Exception {

    }

    public void testParseGeo() throws Exception {

    }

    public void testParseOrganizer() throws Exception {

    }
}
