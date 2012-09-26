package se.pausemode.ICSHandler.TestClasses;

import junit.framework.TestCase;
import se.pausemode.ICSHandler.DataTypes.*;
import se.pausemode.ICSHandler.Util.ParserUtil;

import java.util.Arrays;

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
        assertNull(ad.getSENT_BY());
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
        assertNull(ad.getSENT_BY());
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
        assertNull(ad.getSENT_BY());
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
        assertNull(ad.getSENT_BY());
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

        ////

        ad = ParserUtil.parseAttendeeData("PARTSTAT=DECLINED:mailto:jsmith@example.com");
        assertEquals(AttendeeData.PARTSTAT_TYPE.DECLINED, ad.getPARTSTAT());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_FROM());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getROLE());
        assertNull(ad.getMEMBER());
        assertNull(ad.getRSVP());
        assertNull(ad.getSENT_BY());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("PARTSTAT=ACCEPTED:mailto:jsmith@example.com");
        assertEquals(AttendeeData.PARTSTAT_TYPE.ACCEPTED, ad.getPARTSTAT());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("PARTSTAT=TENTATIVE:mailto:jsmith@example.com");
        assertEquals(AttendeeData.PARTSTAT_TYPE.TENTATIVE, ad.getPARTSTAT());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("PARTSTAT=DELEGATED:mailto:jsmith@example.com");
        assertEquals(AttendeeData.PARTSTAT_TYPE.DELEGATED, ad.getPARTSTAT());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("PARTSTAT=NEEDS-ACTION:mailto:jsmith@example.com");
        assertEquals(AttendeeData.PARTSTAT_TYPE.NEEDS_ACTION, ad.getPARTSTAT());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("PARTSTAT=DOUBTFUL:mailto:jsmith@example.com");
        assertEquals(AttendeeData.PARTSTAT_TYPE.OTHER, ad.getPARTSTAT());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("RSVP=TRUE:mailto:jsmith@example.com");
        assertTrue(ad.getRSVP());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_FROM());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getROLE());
        assertNull(ad.getMEMBER());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getSENT_BY());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("RSVP=FALSE:mailto:jsmith@example.com");
        assertFalse(ad.getRSVP());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("DELEGATED-TO=\"mailto:jdoe@example.com\",\"mailto:jqpublic@example.com\":mailto:jsmith@example.com");
        pd = new PeopleData();
        pd.setMembers(new String[]{"mailto:jdoe@example.com", "mailto:jqpublic@example.com"});
        assertEquals(pd, ad.getDELEGATED_TO());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_FROM());
        assertNull(ad.getMEMBER());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertNull(ad.getSENT_BY());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("DELEGATED-TO=\"mailto:jdoe@example.com\":mailto:jsmith@example.com");
        pd = new PeopleData();
        pd.setMembers(new String[]{"mailto:jdoe@example.com"});
        assertEquals(pd, ad.getDELEGATED_TO());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("DELEGATED-FROM=\"mailto:jdoe@example.com\",\"mailto:jqpublic@example.com\":mailto:jsmith@example.com");
        pd = new PeopleData();
        pd.setMembers(new String[]{"mailto:jdoe@example.com", "mailto:jqpublic@example.com"});
        assertEquals(pd, ad.getDELEGATED_FROM());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getMEMBER());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertNull(ad.getSENT_BY());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("DELEGATED-FROM=\"mailto:jdoe@example.com\":mailto:jsmith@example.com");
        pd = new PeopleData();
        pd.setMembers(new String[]{"mailto:jdoe@example.com"});
        assertEquals(pd, ad.getDELEGATED_FROM());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("SENT-BY=\"mailto:sray@example.com\":mailto:jsmith@example.com");
        pd = new PeopleData();
        pd.setMembers(new String[]{"mailto:sray@example.com"});
        assertEquals(pd, ad.getSENT_BY());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getCN());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getMEMBER());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertNull(ad.getDELEGATED_FROM());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("CN=\"John Smith\":mailto:jsmith@example.com");
        assertEquals("John Smith", ad.getCN());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getSENT_BY());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getMEMBER());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertNull(ad.getDELEGATED_FROM());
        assertEquals("mailto:jsmith@example.com", ad.getURI());

        ad = ParserUtil.parseAttendeeData("CN=\"John \"Tomatohead\" Smith\":mailto:jsmith@example.com");
        assertEquals("John \"Tomatohead\" Smith", ad.getCN());

        ad = ParserUtil.parseAttendeeData("CN=Mr. No-quotes:mailto:jsmith@example.com");
        assertEquals("Mr. No-quotes", ad.getCN());

        ////

        ad = ParserUtil.parseAttendeeData("DIR=\"ldap://example.com:6666/o=ABC%20Industries,c=US???(cn=Jim%20Dolittle)\":mailto:jimdo@example.com");
        assertEquals("ldap://example.com:6666/o=ABC%20Industries,c=US???(cn=Jim%20Dolittle)", ad.getDIR());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getSENT_BY());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getMEMBER());
        assertNull(ad.getCN());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertNull(ad.getDELEGATED_FROM());
        assertEquals("mailto:jimdo@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("LANGUAGE=en:Germany;DIR=\"ldap://example.com:6666/o=ABC%20Industries,c=US???(cn=Jim%20Dolittle)\":mailto:jimdo@example.com");
        assertEquals("en:Germany", ad.getLANGUAGE());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getSENT_BY());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getMEMBER());
        assertNull(ad.getCN());
        assertEquals("ldap://example.com:6666/o=ABC%20Industries,c=US???(cn=Jim%20Dolittle)", ad.getDIR());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertNull(ad.getDELEGATED_FROM());
        assertEquals("mailto:jimdo@example.com", ad.getURI());

        ////

        ad = ParserUtil.parseAttendeeData("CN=\"John Smith\";INVALIDPARAM=Terminator:mailto:jsmith@example.com");
        assertEquals("John Smith", ad.getCN());
        assertNull(ad.getCALADDRESS());
        assertNull(ad.getSENT_BY());
        assertNull(ad.getCUTYPE());
        assertNull(ad.getDELEGATED_TO());
        assertNull(ad.getMEMBER());
        assertNull(ad.getDIR());
        assertNull(ad.getLANGUAGE());
        assertNull(ad.getPARTSTAT());
        assertNull(ad.getROLE());
        assertNull(ad.getRSVP());
        assertNull(ad.getDELEGATED_FROM());
        assertEquals("mailto:jsmith@example.com", ad.getURI());
    }

    public void testParsePeopleData() throws Exception {
        PeopleData pd;
        PeopleData expected;
        assertNull(ParserUtil.parsePeopleData(null));

        pd = ParserUtil.parsePeopleData("\"Kalle\",\"Bertil\",\"Ove\"");
        expected = new PeopleData();
        expected.setMembers(new String[]{"Kalle", "Bertil", "Ove"});
        assertEquals(expected, pd);


        pd = ParserUtil.parsePeopleData("\"HasseBrasse\"");
        expected = new PeopleData();
        expected.setMembers(new String[]{"HasseBrasse"});
        assertEquals(expected, pd);

        pd = ParserUtil.parsePeopleData("\"Göran \"pizzaälskaren\" Poulssen\"");
        expected = new PeopleData();
        expected.setMembers(new String[]{"Göran \"pizzaälskaren\" Poulssen"});
        assertEquals(expected, pd);
    }

    public void testParseAttachData() throws Exception {
        AttachData ad = null;
        AttachData expected = null;
        assertNull(ParserUtil.parseAttachData(null));

        ad = ParserUtil.parseAttachData("CID:jsmith.part3.960817T083000.xyzMail@example.com");
        expected = new AttachData();
        expected.setURI("CID:jsmith.part3.960817T083000.xyzMail@example.com");
        assertEquals(expected, ad);

        ad = ParserUtil.parseAttachData("FMTTYPE=application/postscript;NONVALID=banana:ftp://example.com/pub/reports/r-960812.ps");
        expected = new AttachData();
        expected.setFMTTYPE("application/postscript");
        expected.setURI("ftp://example.com/pub/reports/r-960812.ps");
        assertEquals(expected, ad);

        ad = ParserUtil.parseAttachData("ENCODING=BASE64;VALUE=BINARY:0110011000");
        expected = new AttachData();
        expected.setENCODING("BASE64");
        expected.setVALUE("BINARY");
        expected.setBINARY("0110011000");
        assertEquals(expected, ad);

        ad = ParserUtil.parseAttachData("FMTTYPE=text/binary;ENCODING=BASE64;VALUE=BINARY:0110011000");
        expected = new AttachData();
        expected.setENCODING("BASE64");
        expected.setVALUE("BINARY");
        expected.setBINARY("0110011000");
        expected.setFMTTYPE("text/binary");
        assertEquals(expected, ad);


    }

    public void testParseDurationData() throws Exception {
        DurationData dd = null;
        DurationData expected = null;
        assertNull(ParserUtil.parseDurationData(null));

        dd = ParserUtil.parseDurationData("PT1H0M0S");
        expected = new DurationData();
        expected.setPosValue(true);
        expected.setDurationType(DurationData.DURATION_TYPE.DUR_TIME);
        expected.setHour(1);
        expected.setMinute(0);
        expected.setSecond(0);
        assertEquals(expected,dd);

        dd = ParserUtil.parseDurationData("PT15M");
        expected = new DurationData();
        expected.setPosValue(true);
        expected.setDurationType(DurationData.DURATION_TYPE.DUR_TIME);
        expected.setHour(0);
        expected.setMinute(15);
        expected.setSecond(0);
        assertEquals(expected,dd);

        dd = ParserUtil.parseDurationData("-PT15M");
        expected = new DurationData();
        expected.setPosValue(false);
        expected.setDurationType(DurationData.DURATION_TYPE.DUR_TIME);
        expected.setHour(0);
        expected.setMinute(15);
        expected.setSecond(0);
        assertEquals(expected,dd);

        dd = ParserUtil.parseDurationData("PT2H15M30S");
        expected = new DurationData();
        expected.setPosValue(true);
        expected.setDurationType(DurationData.DURATION_TYPE.DUR_TIME);
        expected.setHour(2);
        expected.setMinute(15);
        expected.setSecond(30);
        assertEquals(expected,dd);

        dd = ParserUtil.parseDurationData("P1DT1H0M0S");
        expected = new DurationData();
        expected.setPosValue(true);
        expected.setDurationType(DurationData.DURATION_TYPE.DUR_DATE);
        expected.setDay(1);
        expected.setHour(1);
        expected.setMinute(0);
        expected.setSecond(0);
        assertEquals(expected,dd);

        dd = ParserUtil.parseDurationData("P2W");
        expected = new DurationData();
        expected.setPosValue(true);
        expected.setDurationType(DurationData.DURATION_TYPE.DUR_WEEK);
        expected.setWeek(2);

        dd = ParserUtil.parseDurationData("OTHERPARAM=nonsense:P1DT1H0M0S");
        expected = new DurationData();
        expected.setPosValue(true);
        expected.setDurationType(DurationData.DURATION_TYPE.DUR_DATE);
        expected.setDay(1);
        expected.setHour(1);
        expected.setMinute(0);
        expected.setSecond(0);
        assertEquals(expected,dd);
    }

    public void testParseRecurrenceRule() throws Exception {
        RecurrenceRuleData rrd = null;
        RecurrenceRuleData expected = null;
        assertNull(ParserUtil.parseRecurrenceRule(null));

        rrd = ParserUtil.parseRecurrenceRule("FREQ=DAILY;COUNT=10");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.DAILY);
        expected.setCOUNT(10);
        expected.setCompleteString("FREQ=DAILY;COUNT=10");
        assertEquals(expected,rrd);

        rrd = ParserUtil.parseRecurrenceRule("OTHERPARAM=ostbage:FREQ=DAILY;COUNT=10");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.DAILY);
        expected.setCOUNT(10);
        expected.setCompleteString("FREQ=DAILY;COUNT=10");
        assertEquals(expected,rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=DAILY;UNTIL=19971224T000000Z");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.DAILY);
        DateData dd = new DateData();
        dd.setValue("19971224T000000Z");
        expected.setUNTIL(dd);
        expected.setCompleteString("FREQ=DAILY;UNTIL=19971224T000000Z");
        assertEquals(expected,rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=DAILY;INTERVAL=2");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.DAILY);
        expected.setINTERVAL(2);
        expected.setCompleteString("FREQ=DAILY;INTERVAL=2");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=DAILY;INTERVAL=10;COUNT=5");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.DAILY);
        expected.setINTERVAL(10);
        expected.setCOUNT(5);
        expected.setCompleteString("FREQ=DAILY;INTERVAL=10;COUNT=5");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=YEARLY;UNTIL=20000131T140000Z;BYMONTH=1;BYDAY=SU,MO");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.YEARLY);
        dd = new DateData();
        dd.setValue("20000131T140000Z");
        expected.setUNTIL(dd);
        expected.setBYMONTH(new int[]{1});
        WeekDayNumData[] wdnd = new WeekDayNumData[]{new WeekDayNumData(RecurrenceRuleData.WEEKDAY.SU), new WeekDayNumData(RecurrenceRuleData.WEEKDAY.MO)};
        expected.setBYDAY(wdnd);
        expected.setCompleteString("FREQ=YEARLY;UNTIL=20000131T140000Z;BYMONTH=1;BYDAY=SU,MO");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=WEEKLY;INTERVAL=2;WKST=SU");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.WEEKLY);
        expected.setINTERVAL(2);
        expected.setWKST(RecurrenceRuleData.WEEKDAY.SU);
        expected.setCompleteString("FREQ=WEEKLY;INTERVAL=2;WKST=SU");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=WEEKLY;UNTIL=19971007T000000Z;WKST=SU;BYDAY=TU,TH");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.WEEKLY);
        dd = new DateData();
        dd.setValue("19971007T000000Z");
        expected.setUNTIL(dd);
        expected.setWKST(RecurrenceRuleData.WEEKDAY.SU);
        expected.setBYDAY(new WeekDayNumData[]{new WeekDayNumData(RecurrenceRuleData.WEEKDAY.TU), new WeekDayNumData(RecurrenceRuleData.WEEKDAY.TH)});
        expected.setCompleteString("FREQ=WEEKLY;UNTIL=19971007T000000Z;WKST=SU;BYDAY=TU,TH");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=MONTHLY;UNTIL=19971224T000000Z;BYDAY=1FR");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.MONTHLY);
        dd = new DateData();
        dd.setValue("19971224T000000Z");
        expected.setUNTIL(dd);
        WeekDayNumData wdndEx = new WeekDayNumData(RecurrenceRuleData.WEEKDAY.FR);
        wdndEx.setOccurence(1);
        expected.setBYDAY(new WeekDayNumData[]{wdndEx});
        expected.setCompleteString("FREQ=MONTHLY;UNTIL=19971224T000000Z;BYDAY=1FR");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=MONTHLY;INTERVAL=2;COUNT=10;BYDAY=1SU,-1SU");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.MONTHLY);
        expected.setINTERVAL(2);
        expected.setCOUNT(10);
        wdndEx = new WeekDayNumData(RecurrenceRuleData.WEEKDAY.SU);
        wdndEx.setOccurence(1);
        WeekDayNumData wdndEx2 = new WeekDayNumData(RecurrenceRuleData.WEEKDAY.SU);
        wdndEx2.setOccurence(-1);
        expected.setBYDAY(new WeekDayNumData[]{wdndEx, wdndEx2});
        expected.setCompleteString("FREQ=MONTHLY;INTERVAL=2;COUNT=10;BYDAY=1SU,-1SU");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=MONTHLY;BYMONTHDAY=-3");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.MONTHLY);
        expected.setBYMONTHDAY(new int[] {-3});
        expected.setCompleteString("FREQ=MONTHLY;BYMONTHDAY=-3");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=YEARLY;INTERVAL=3;COUNT=10;BYYEARDAY=1,100,200");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.YEARLY);
        expected.setINTERVAL(3);
        expected.setCOUNT(10);
        expected.setBYYEARDAY(new int[]{1, 100, 200});
        expected.setCompleteString("FREQ=YEARLY;INTERVAL=3;COUNT=10;BYYEARDAY=1,100,200");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=YEARLY;BYWEEKNO=20;BYDAY=MO");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.YEARLY);
        expected.setBYWEEKNO(new int[]{20});
        expected.setBYDAY(new WeekDayNumData[] {new WeekDayNumData(RecurrenceRuleData.WEEKDAY.MO)});
        expected.setCompleteString("FREQ=YEARLY;BYWEEKNO=20;BYDAY=MO");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=MONTHLY;COUNT=3;BYDAY=TU;BYSETPOS=3");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.MONTHLY);
        expected.setCOUNT(3);
        expected.setBYDAY(new WeekDayNumData[]{new WeekDayNumData(RecurrenceRuleData.WEEKDAY.TU)});
        expected.setBYSETPOS(new int[]{3});
        expected.setCompleteString("FREQ=MONTHLY;COUNT=3;BYDAY=TU;BYSETPOS=3");
        assertEquals(expected, rrd);

        rrd = ParserUtil.parseRecurrenceRule("FREQ=MINUTELY;INTERVAL=15;COUNT=6");
        expected = new RecurrenceRuleData();
        expected.setFREQ(RecurrenceRuleData.FREQVALUE.MINUTELY);
        expected.setINTERVAL(15);
        expected.setCOUNT(6);
        expected.setCompleteString("FREQ=MINUTELY;INTERVAL=15;COUNT=6");
        assertEquals(expected, rrd);
    }

    public void testParseWeekDay() throws Exception {
        WeekDayNumData[] wd = null;
        WeekDayNumData[] expected = null;
        assertNull(ParserUtil.parseWeekDay(null));

        wd = ParserUtil.parseWeekDay(new String[]{"SU"});
        expected = new WeekDayNumData[]{new WeekDayNumData(RecurrenceRuleData.WEEKDAY.SU)};
        assertEqualArrays(expected, wd);

        wd = ParserUtil.parseWeekDay(new String[]{"-1MO"});
        WeekDayNumData temp = new WeekDayNumData(RecurrenceRuleData.WEEKDAY.MO);
        temp.setOccurence(-1);
        expected = new WeekDayNumData[]{temp};
        assertEqualArrays(expected, wd);

        wd = ParserUtil.parseWeekDay(new String[]{"1SU","-2SU"});
        temp = new WeekDayNumData(RecurrenceRuleData.WEEKDAY.SU);
        temp.setOccurence(1);
        WeekDayNumData temp2 = new WeekDayNumData(RecurrenceRuleData.WEEKDAY.SU);
        temp2.setOccurence(-2);
        expected = new WeekDayNumData[]{temp, temp2};
        assertEqualArrays(expected, wd);

        wd = ParserUtil.parseWeekDay(new String[]{"MO","TU","2WE"});
        temp = new WeekDayNumData(RecurrenceRuleData.WEEKDAY.WE);
        temp.setOccurence(2);
        expected = new WeekDayNumData[]{new WeekDayNumData(RecurrenceRuleData.WEEKDAY.MO), new WeekDayNumData(RecurrenceRuleData.WEEKDAY.TU), temp};
        assertEqualArrays(expected, wd);
    }

    public void testParseStringData() throws Exception {
        StringData sd = null;
        StringData expected = null;

        assertNull(ParserUtil.parseStringData(null));

        sd = ParserUtil.parseStringData("Meeting to provide technical review for \"Phoenix\" design.\\nHappy Face Conference Room. Phoenix design team MUST attend this meeting.\\nRSVP to team leader.");
        expected = new StringData();
        expected.setString("Meeting to provide technical review for \"Phoenix\" design.\\nHappy Face Conference Room. Phoenix design team MUST attend this meeting.\\nRSVP to team leader.");
        assertEquals(expected, sd);

        sd = ParserUtil.parseStringData("");
        expected = new StringData();
        expected.setString("");
        assertEquals(expected, sd);

        sd = ParserUtil.parseStringData("ALTREP=\"CID:part3.msg.970415T083000@example.com\":Blaha");
        expected = new StringData();
        expected.setString("Blaha");
        expected.setAltrep("CID:part3.msg.970415T083000@example.com");
        assertEquals(expected, sd);

        sd = ParserUtil.parseStringData("LANGUAGE=pirate:Argh!");
        expected = new StringData();
        expected.setString("Argh!");
        expected.setLanguage("pirate");
        assertEquals(expected, sd);

        sd = ParserUtil.parseStringData("WHAAA=Scary:Hello cutypie!");
        expected = new StringData();
        expected.setString("Hello cutypie!");
        assertEquals(expected,sd);
    }

    public void testParseDateData() throws Exception {
        DateData dd, expected;
        assertNull(ParserUtil.parseDateData(null));

        dd = ParserUtil.parseDateData("19960401T150000Z");
        expected = new DateData();
        expected.setValue("19960401T150000Z");
        assertEquals(expected, dd);

        dd = ParserUtil.parseDateData("VALUE=DATE:19980704");
        expected = new DateData();
        expected.setValue("19980704");
        expected.setValue_type(DateData.VALUE_TYPE.DATE);
        assertEquals(expected, dd);

        dd = ParserUtil.parseDateData("VALUE=DATE-TIME;TZID=Copenhagen:19960401T150000Z");
        expected = new DateData();
        expected.setValue("19960401T150000Z");
        expected.setValue_type(DateData.VALUE_TYPE.DATE_TIME);
        expected.setTZID("Copenhagen");
        assertEquals(expected, dd);

        dd = ParserUtil.parseDateData("VALUE=DATE;FEDTMULE=Alfons:19980704");
        expected = new DateData();
        expected.setValue("19980704");
        expected.setValue_type(DateData.VALUE_TYPE.DATE);
        assertEquals(expected, dd);
    }

    public void testParseRecurrenceID() throws Exception {
        RecurrenceIDData ridd, expected;
        assertNull(ParserUtil.parseRecurrenceID(null));

        ridd = ParserUtil.parseRecurrenceID("19960401T150000Z");
        expected = new RecurrenceIDData();
        expected.setValue("19960401T150000Z");
        assertEquals(expected, ridd);

        ridd = ParserUtil.parseRecurrenceID("VALUE=DATE:19980704");
        expected = new RecurrenceIDData();
        expected.setValue("19980704");
        expected.setValue_type(DateData.VALUE_TYPE.DATE);
        assertEquals(expected, ridd);

        ridd = ParserUtil.parseRecurrenceID("VALUE=DATE-TIME;TZID=Copenhagen:19960401T150000Z");
        expected = new RecurrenceIDData();
        expected.setValue("19960401T150000Z");
        expected.setValue_type(DateData.VALUE_TYPE.DATE_TIME);
        expected.setTZID("Copenhagen");
        assertEquals(expected, ridd);

        ridd = ParserUtil.parseRecurrenceID("VALUE=DATE;FEDTMULE=Alfons:19980704");
        expected = new RecurrenceIDData();
        expected.setValue("19980704");
        expected.setValue_type(DateData.VALUE_TYPE.DATE);
        assertEquals(expected, ridd);

        ridd = ParserUtil.parseRecurrenceID("VALUE=DATE;FEDTMULE=Alfons:19980704");
        expected = new RecurrenceIDData();
        expected.setValue("19980704");
        expected.setValue_type(DateData.VALUE_TYPE.DATE);
        assertEquals(expected, ridd);
    }

    public void testParseGeo() throws Exception {
        PositionData pd, expected;
        assertNull(ParserUtil.parseGeo(null));

        pd = ParserUtil.parseGeo("37.386013;-122.082932");
        expected = new PositionData(37.386013f,-122.082932f);
        assertEquals(expected,pd);

        pd = ParserUtil.parseGeo("HELLO=banana:37.386013;-122.082932");
        expected = new PositionData(37.386013f,-122.082932f);
        assertEquals(expected,pd);
    }

    public void testParseOrganizer() throws Exception {
        OrganizerData od, expected;
        assertNull(ParserUtil.parseOrganizer(null));

        od = ParserUtil.parseOrganizer("CN=John Smith:mailto:jsmith@example.com");
        expected = new OrganizerData();
        expected.setCommonName("John Smith");
        expected.setCalAddress("mailto:jsmith@example.com");
        assertEquals(expected, od);

        od = ParserUtil.parseOrganizer("CN=JohnSmith;DIR=\"ldap://example.com:6666/o=DC%20Associates,c=US???(cn=John%20Smith)\":mailto:jsmith@example.com");
        expected = new OrganizerData();
        expected.setCommonName("JohnSmith");
        expected.setDirectory("ldap://example.com:6666/o=DC%20Associates,c=US???(cn=John%20Smith)");
        expected.setCalAddress("mailto:jsmith@example.com");
        assertEquals(expected, od);

        od = ParserUtil.parseOrganizer("SENT-BY=\"mailto:jane_doe@example.com\":mailto:jsmith@example.com");
        expected = new OrganizerData();
        expected.setSentBy("mailto:jane_doe@example.com");
        expected.setCalAddress("mailto:jsmith@example.com");
        assertEquals(expected, od);

        od = ParserUtil.parseOrganizer("LANGUAGE=German;SENT-BY=\"mailto:jane_doe@example.com\":mailto:jsmith@example.com");
        expected = new OrganizerData();
        expected.setSentBy("mailto:jane_doe@example.com");
        expected.setCalAddress("mailto:jsmith@example.com");
        expected.setLanguage("German");
        assertEquals(expected, od);

        od = ParserUtil.parseOrganizer("CN=John Smith;TEST=Fart:mailto:jsmith@example.com");
        expected = new OrganizerData();
        expected.setCommonName("John Smith");
        expected.setCalAddress("mailto:jsmith@example.com");
        assertEquals(expected, od);
    }

    private void assertEqualArrays(Object[] expected, Object[] actual){
        assertEquals(Arrays.asList(expected),Arrays.asList(actual));
    }
}
