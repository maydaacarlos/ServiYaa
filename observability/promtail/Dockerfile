FROM grafana/promtail:3.4.1
COPY config.yml /promtail.yml
ENTRYPOINT ["/usr/bin/promtail"]
CMD ["-config.file=/promtail.yml"]
