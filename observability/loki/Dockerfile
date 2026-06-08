FROM grafana/loki:3.4.1
COPY config.yml /loki.yml
ENTRYPOINT ["/usr/bin/loki"]
CMD ["-config.file=/loki.yml"]
