package com.jpsoft.onlinemusicplayer.network;

public interface ApiUtils {
    String BASE_URL = "http://darbar.arkhairul.xyz/api/";
    String IMAGE_BASE_URL =  "http://darbar.arkhairul.xyz/";

    String PRAYER_TIME = BASE_URL + "month-wise-prayer-time?";
    String OTP_VALIDATE = BASE_URL + "verify-otp";
    String OTP_RESEND = BASE_URL + "resend-otp";
    String REGISTRATION_1 = BASE_URL + "registration-step-1";

    String HADITH_TYPE = BASE_URL + "hadith-type";
    String HADITH_TROPIC = BASE_URL + "hadith-topics?type_id=";
    String HADITH_INFO = BASE_URL + "hadith-info?topic_id=";

    String COUNTRY = BASE_URL + "country";
    String DIVISION = BASE_URL + "division?country_id=";
    String DISTRIC = BASE_URL + "district?division_id=";
    String THANA = BASE_URL + "thana?district_id=";
    String POST_OFFICE = BASE_URL + "postoffice?district_id=";
    String REGISTRATION_2 = BASE_URL + "registration-step-2";
    String LOGIN = BASE_URL + "auth-login";


    String QURAN_RECITATION = BASE_URL + "recitation?verse_id=";
    String QURAN_RECITATION_ALL = BASE_URL +"verse?surah_id=";


    //Sufi Apis
    String SUFI_TOPICS = BASE_URL + "sufi-teaching-topic";
    String SUFI_LESSONS = BASE_URL + "sufi-teaching-lesson?topic_id=";

    String SUFI_TOPIC_PINED_REQUEST = BASE_URL + "sufi-teaching-pinned-topic-request";
    String SUFI_LESSON_PINED_REQUEST = BASE_URL + "sufi-teaching-pinned-lesson-request";

    //Sufi Images
    String SUFI_IMAGE_BASE_URL =  "http://darbar.arkhairul.xyz/";


    //AtmarBani
    String ATMAR_BANI_TOPIC = BASE_URL + "atmar-bani";

    String ATMAR_BANI_NEWS = BASE_URL + "topic-wise-atmar-bani-news?topic_id=";

    //Events
    String PUBLIC_EVENT = BASE_URL + "public-events";
    String PRIVATE_EVENT = BASE_URL + "private-events";
    String GROUP_EVENT = BASE_URL + "member-wise-events-list?member_id=";

    //Password Update
    String PASSWORD_UPDATE_URL = BASE_URL+"update-password";

    //My Sufi
    String MY_SUFI_URL = BASE_URL+"sufi-teaching-pinned-topic?member_id=";

    String MY_LESSON_URL = BASE_URL+"sufi-teaching-pinned-lesson?member_id=";



    //
    String WAZIFA_TOPIC_URL = BASE_URL+"wazifa";
    String WAZIFA_CONTENT_URL = BASE_URL+"topic-wise-wazifa-content?topic_id=";


    //Member request to join in a private group
    String MEMBER_JOIN_REQUEST = BASE_URL+"join-private-group";

    //Get member groups list based on member id
    String GROUP_LIST_URL = BASE_URL+"groups?member_id=";

    //Get all group members list based on group_id
    String MEMBER_LIST_URL = BASE_URL+"group-members?group_id=";


    //Get all approved group member list
    String APPROVED_MEMBERS_URL = BASE_URL+"approved-group-members?group_id=";


    //Get all approved group member list
    String PENDING_MEMBERS_URL = BASE_URL+"pending-group-members?group_id=";

    //Approve group members by group moderator
    String APPROVED_MEMBER_URL = BASE_URL+"approve-group-members";

    //Weekly Dewanbag
    String WEEKLY_DAWANBAG_TOPIC_URL = BASE_URL+"weekly-dewanbag";
    String WEEKLY_DAWANBAG_DETAILS_URL = BASE_URL+"topic-wise-weekly-dewanbag-news?topic_id=";

    //Weekly Dewanbag
    String MONTHLY_DAWANBAG_TOPIC_URL = BASE_URL+"monthly-dewanbag";
    String MONTHLY_DAWANBAG_DETAILS_URL = BASE_URL+"topic-wise-monthly-dewanbag-news?topic_id=";

    //Counter
    String COUNTER_URL = BASE_URL+"dashboard-counter?member_id=";

    //Group Report
    String GROUP_REPORT_URL = BASE_URL+"report-group";

    //Group Leave
    String GROUP_LEAVE_URL = BASE_URL+"leave-group";

    //Comments Urls for AtmarBani
    String ATMAR_BANI_GET_COMMENTS_URL = BASE_URL+"atmar-bani/news/comments?news_id=";
    String ATMAR_BANI_POST_COMMENTS_URL = BASE_URL+"atmar-bani/post/comments";

    //Comments Urls for Weekly Dewanbag
    String WEEKLY_DEWANBAG_GET_COMMENTS_URL = BASE_URL+"weekly-dewanbag/news/comments?news_id=";
    String WEEKLY_DEWANBAG_POST_COMMENTS_URL = BASE_URL+"weekly-dewanbag/post/comments";

    //Comments Urls for Monthly Dewanbag
    String MONTHLY_DEWANBAG_GET_COMMENTS_URL = BASE_URL+"monthly-dewanbag/news/comments?news_id=";
    String MONTHLY_DEWANBAG_POST_COMMENTS_URL = BASE_URL+"monthly-dewanbag/post/comments";

    //Feedback
    String FEEDBACK_URL = BASE_URL+"send-feedback";

    //Banner
    String BANNER_URL = BASE_URL+"banner";
}
