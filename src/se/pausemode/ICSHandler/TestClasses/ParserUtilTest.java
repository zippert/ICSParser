package se.pausemode.ICSHandler.TestClasses;

import junit.framework.TestCase;
import se.pausemode.ICSHandler.DataTypes.*;
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
        StringData sd = null;
        assertNull(ParserUtil.parseCategoriesData(null));

        sd = ParserUtil.parseCategoriesData("APPOINTMENT,EDUCATION");
        assertNotNull(sd);
        assertNull(sd.getLanguage());
        assertNull(sd.getAltrep());
        assertEquals("APPOINTMENT,EDUCATION", sd.getString());

        sd = ParserUtil.parseCategoriesData("MEETING");
        assertNotNull(sd);
        assertNull(sd.getLanguage());
        assertNull(sd.getAltrep());
        assertEquals("MEETING", sd.getString());

        sd = ParserUtil.parseCategoriesData("LANGUAGE=en-US;BANANARAMA=Tomato:MEETING");
        assertNotNull(sd);
        assertEquals("en-US", sd.getLanguage());
        assertNull(sd.getAltrep());
        assertEquals("MEETING", sd.getString());

    }

    public void testParseAttendeeData() throws Exception {
        AttendeeData ad = null;
        assertNull(ParserUtil.parseAttendeeData(null));

        ad = ParserUtil.parseAttendeeData("CUTYPE=INDIVIDUAL:mailto:ietf-calsch@example.org");
        assertNull(ad.getMEMBER());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertEquals(AttendeeData.CUTYPE_TYPES.INDIVIDUAL, ad.getCUTYPE());
        assertNull(ad.getDELEGATED_FROM());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertEquals("mailto:ietf-calsch@example.org", ad.getURI());

        ad = ParserUtil.parseAttendeeData("CUTYPE=GROUP:mailto:ietf-calsch@example.org");
        assertEquals(AttendeeData.CUTYPE_TYPES.GROUP, ad.getCUTYPE());
        assertEquals("mailto:ietf-calsch@example.org", ad.getURI());

        ad = ParserUtil.parseAttendeeData("CUTYPE=RESOURCE:mailto:ietf-calsch@example.org");
        assertEquals(AttendeeData.CUTYPE_TYPES.RESOURCE, ad.getCUTYPE());
        assertEquals("mailto:ietf-calsch@example.org", ad.getURI());

        ad = ParserUtil.parseAttendeeData("CUTYPE=ROOM:mailto:ietf-calsch@example.org");
        assertEquals(AttendeeData.CUTYPE_TYPES.ROOM, ad.getCUTYPE());
        assertEquals("mailto:ietf-calsch@example.org", ad.getURI());

        ad = ParserUtil.parseAttendeeData("CUTYPE=UNKNOWN:mailto:ietf-calsch@example.org");
        assertEquals(AttendeeData.CUTYPE_TYPES.UNKNOWN, ad.getCUTYPE());
        assertEquals("mailto:ietf-calsch@example.org", ad.getURI());

        ad = ParserUtil.parseAttendeeData("CUTYPE=FISKBEN:mailto:ietf-calsch@example.org");
        assertNull(ad.getMEMBER());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertEquals(AttendeeData.CUTYPE_TYPES.OTHER, ad.getCUTYPE());
        assertNull(ad.getDELEGATED_FROM());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertEquals("mailto:ietf-calsch@example.org", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("MEMBER=\"mailto:DEV-GROUP@example.com\":mailto:joecool@example.com");
        PeopleData pd = new PeopleData();
        pd.setMembers(new String[]{"mailto:DEV-GROUP@example.com"});
        assertEquals(pd, ad.getMEMBER());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_FROM());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertEquals("mailto:joecool@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("MEMBER=\"mailto:projectA@example.com\",\"mailto:projectB@example.com\":mailto:janedoe@example.com");
        pd = new PeopleData();
        pd.setMembers(new String[]{"mailto:projectA@example.com", "mailto:projectB@example.com"});
        assertEquals(pd, ad.getMEMBER());
        assertEquals("mailto:janedoe@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("ROLE=CHAIR:mailto:mrbig@example.com");
        assertEquals(AttendeeData.ROLE_TYPES.CHAIR, ad.getROLE());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_FROM());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getMEMBER());
        assertNull(ad.getRSVP());
        assertEquals("mailto:mrbig@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("ROLE=REQ-PARTICIPANT:mailto:mrbig@example.com");
        assertEquals(AttendeeData.ROLE_TYPES.REQ_PARTICIPANT, ad.getROLE());
        assertEquals("mailto:mrbig@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("ROLE=OPT-PARTICIPANT:mailto:mrbig@example.com");
        assertEquals(AttendeeData.ROLE_TYPES.OPT_PARTICIPANT, ad.getROLE());
        assertEquals("mailto:mrbig@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("ROLE=NON-PARTICIPANT:mailto:mrbig@example.com");
        assertEquals(AttendeeData.ROLE_TYPES.NON_PARTICIPANT, ad.getROLE());
        assertEquals("mailto:mrbig@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("ROLE=PANNKAKSBAGARE:mailto:mrbig@example.com");
        assertEquals(AttendeeData.ROLE_TYPES.OTHER, ad.getROLE());
        assertEquals("mailto:mrbig@example.com", ad.getURI());

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
