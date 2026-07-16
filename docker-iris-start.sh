#!/bin/bash
set -e

/tini -- /docker-entrypoint.sh iris &
entry_pid=$!

wait_for_iris() {
  for i in $(seq 1 90); do
    if echo 'Halt' | iris session IRIS -U IRISAPP >/dev/null 2>&1; then
      return 0
    fi
    sleep 2
  done
  return 1
}

wait_for_jgw() {
  for i in $(seq 1 90); do
    if timeout 1 bash -c 'cat < /dev/null > /dev/tcp/jgw/55555' 2>/dev/null; then
      return 0
    fi
    sleep 2
  done
  return 1
}

wait_for_iris
wait_for_jgw

if [ ! -f /opt/irisapp/.pex-setup-done ]; then
  if iris session IRIS < /opt/irisapp/pex-runtime.script; then
    touch /opt/irisapp/.pex-setup-done
  else
    echo "PEX runtime setup failed" >&2
    exit 1
  fi
fi

wait $entry_pid
