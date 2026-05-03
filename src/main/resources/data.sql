INSERT INTO love_types (type_code, name_ko, description)
VALUES
    ('YANGBAN',  '양반',  '예의를 중시하고 밀당을 즐기며 천천히 타오르는 스타일'),
    ('JANGGUN',  '장군',  '눈치 안 보고 돌진! 내 사람은 내가 지킨다는 파워 직진 스타일'),
    ('SATTO',    '사또',  '내가 계획한 대로 따라와야 직성이 풀리는 리더 스타일'),
    ('DOLSOE',   '돌쇠',  '무조건적인 사랑. 궂은일도 다 해주는 조선 최고의 사랑꾼'),
    ('WANGJOK',  '왕족',  '사랑받는 게 당연한 타입. 먼저 다가가기보다 기다리는 스타일'),
    ('GWANGDAE', '광대',  '구속은 싫소! 입담과 유머로 상대를 매일 웃게 만드는 스타일')
ON DUPLICATE KEY UPDATE
    name_ko     = VALUES(name_ko),
    description = VALUES(description);
