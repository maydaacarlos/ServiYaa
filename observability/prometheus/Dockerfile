FROM prom/prometheus:v3.5.0
COPY prometheus.yml /prometheus.yml
COPY prometheus-dev.yml /prometheus-dev.yml
ENTRYPOINT ["/bin/prometheus"]
CMD ["--config.file=/prometheus.yml", "--storage.tsdb.path=/prometheus"]
