DROP TABLE IF EXISTS stats CASCADE;

CREATE TABLE IF NOT EXISTS stats (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    app VARCHAR(64) NOT NULL,
    uri VARCHAR(512) NOT NULL,
    ip VARCHAR(64) NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_stats PRIMARY KEY (id)
    )
