Project: 융과 사는 남자 - Logical Schema (3NF)

Love_Type (ID, Type_Code, Name_KO, Description)

설명: 조선시대 연애 유형 기준 정보 마스터 테이블

Type_Compatibility (ID, From_Type_ID, To_Type_ID)

FK: From_Type_ID -> Love_Type(ID), To_Type_ID -> Love_Type(ID)

User (ID, Instagram_ID, Password, Gender, MBTI, Love_Type_ID, Introduction, Emoji, Ticket_Count, Deleted)

FK: Love_Type_ID -> Love_Type(ID)

특이사항: 계정 정보와 프로필 카드 정보가 통합된 형태 (Merged Entity)

Ticket_Transaction (ID, User_ID, Delta, Reason, Created_At)

FK: User_ID -> User(ID)

Access_Log (ID, Viewer_ID, Target_User_ID, Accessed_At)

FK: Viewer_ID -> User(ID), Target_User_ID -> User(ID)