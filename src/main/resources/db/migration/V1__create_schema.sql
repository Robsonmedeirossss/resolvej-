CREATE SCHEMA IF NOT EXISTS sc_resolveja;

SET search_path TO sc_resolveja;

CREATE TABLE IF NOT EXISTS users
(
    id                          BIGINT,
    name                        VARCHAR(250)    NOT NULL,
    email                       VARCHAR(250)    NOT NULL    UNIQUE,
    password                    VARCHAR(750)    NOT NULL,
    image_url                   VARCHAR(1500),    
    birth_date                  DATE                NOT NULL,
    active                      BOOLEAN         NOT NULL    DEFAULT     true,
    role                        VARCHAR(25)     NOT NULL    DEFAULT     'CUSTOMER',
    created_at                  TIMESTAMP       NOT NULL    DEFAULT     CURRENT_TIMESTAMP,
    updated_at                  TIMESTAMP,

    CONSTRAINT pk_users PRIMARY KEY(id),
    CONSTRAINT ck_role_user CHECK (
        role IN(
            'CUSTOMER',
            'PROVIDER',
            'ADMIN'
        )
    )
);

CREATE INDEX idx_user_role ON users(role);

CREATE TABLE IF NOT EXISTS services
(
    id                          BIGINT,
    name                        VARCHAR(250)    NOT NULL,
    description                 VARCHAR(1000),
    icon_url                    VARCHAR(1500),
    active                      BOOLEAN         NOT NULL    DEFAULT     true,
    created_at                  TIMESTAMP       NOT NULL    DEFAULT     CURRENT_TIMESTAMP,
    updated_at                  TIMESTAMP,

    CONSTRAINT pk_services PRIMARY KEY(id),
    CONSTRAINT uq_service_name UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS provider_services
(
    id                          BIGINT,
    name                        VARCHAR(250)    NOT NULL,
    description                 VARCHAR(1500),
    active                      BOOLEAN         NOT NULL    DEFAULT     true,
    price                       NUMERIC(7,2)    NOT NULL,
    service_id                  BIGINT          NOT NULL,
    provider_id                 BIGINT          NOT NULL,
    created_at                  TIMESTAMP       NOT NULL    DEFAULT     CURRENT_TIMESTAMP,
    updated_at                  TIMESTAMP,

    CONSTRAINT pk_provider_services PRIMARY KEY(id),
    CONSTRAINT ck_price  CHECK (price >= 0),
    CONSTRAINT fk_provider_services_service FOREIGN KEY(service_id) REFERENCES services(id),
    CONSTRAINT fk_provider_services_provider FOREIGN KEY(provider_id) REFERENCES users(id),
    CONSTRAINT uq_provider_service UNIQUE(provider_id, service_id)
);

CREATE INDEX idx_provider_services_provider ON provider_services(provider_id);
CREATE INDEX idx_provider_services_service ON provider_services(service_id);

CREATE TABLE IF NOT EXISTS service_solicitations
(
    id                          BIGINT,
    customer_id                 BIGINT          NOT NULL,
    provider_service_id         BIGINT          NOT NULL,
    status                      VARCHAR(20)     NOT NULL,
    price                       NUMERIC(7,2)    NOT NULL,
    scheduled_at                TIMESTAMP       NOT NULL,
    completed_at                TIMESTAMP,
    created_at                  TIMESTAMP       NOT NULL    DEFAULT     CURRENT_TIMESTAMP,
    updated_at                  TIMESTAMP,

    CONSTRAINT pk_service_solicitation PRIMARY KEY(id),
    CONSTRAINT fk_customer_service_solicitation FOREIGN KEY(customer_id) REFERENCES users(id),
    CONSTRAINT fk_provider_service_solicitation FOREIGN KEY(provider_service_id) REFERENCES provider_services(id),
    CONSTRAINT ck_service_solicitation_status CHECK (
        status IN(
            'PENDING',
            'CONFIRMED',
            'REALIZED',
            'CANCELED'
        )
    ),
    CONSTRAINT ck_price_solicitation CHECK(
        price >= 0
    )
);

CREATE INDEX idx_solicitation_customer ON service_solicitations(customer_id);
CREATE INDEX idx_solicitation_provider_services ON service_solicitations(provider_service_id);
CREATE INDEX idx_solicitation_status ON service_solicitations(status);

CREATE TABLE IF NOT EXISTS ratings
(
    id                          BIGINT         NOT NULL,
    note                        SMALLINT        NOT NULL,
    comment                     VARCHAR(500),
    status                      VARCHAR(20)     NOT NULL    DEFAULT     'PENDENTE',
    service_solicitation_id     BIGINT          NOT NULL,
    created_at                  TIMESTAMP       NOT NULL    DEFAULT     CURRENT_TIMESTAMP,

    CONSTRAINT pk_ratings PRIMARY KEY(id),
    CONSTRAINT fk_ratings_services_solicitation FOREIGN KEY(service_solicitation_id) REFERENCES service_solicitations(id),
    CONSTRAINT ck_ratings_status CHECK(
        status IN(
            'PENDING',
            'APPROVED',
            'REJECTED'
        )
    ),
    CONSTRAINT check_rating_interval CHECK(
        note BETWEEN 1 AND 5
    ),

    CONSTRAINT uq_rating_solicitation UNIQUE(service_solicitation_id)
);


CREATE SEQUENCE IF NOT EXISTS seq_users
    INCREMENT 1 START 1 CACHE 1;

CREATE SEQUENCE IF NOT EXISTS seq_services
    INCREMENT 1 START 1 CACHE 1;

CREATE SEQUENCE IF NOT EXISTS seq_provider_services
    INCREMENT 1 START 1 CACHE 1;

CREATE SEQUENCE IF NOT EXISTS seq_service_solicitations
    INCREMENT 1 START 1 CACHE 1;

CREATE SEQUENCE IF NOT EXISTS seq_ratings
    INCREMENT 1 START 1 CACHE 1;