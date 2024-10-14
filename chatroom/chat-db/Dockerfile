FROM postgres:15

ENV POSTGRES_USER=mychatuser
ENV POSTGRES_PASSWORD=mychatpassword
ENV POSTGRES_DB=mychatdb

COPY ./init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432