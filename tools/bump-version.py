#!/usr/bin/env python3
"""Bump the app's single version in pom.xml, without building/committing/deploying."""
import argparse
from pathlib import Path
import re


def next_version(current, kind):
    major, minor, patch = map(int, current.split('.'))
    if major != 1:
        raise ValueError('Major version changes require an explicit project decision')
    return f'1.{minor + 1}.0' if kind == 'feature' else f'1.{minor}.{patch + 1}'


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('kind', choices=['feature', 'fix'])
    parser.add_argument('--dry-run', action='store_true')
    args = parser.parse_args()
    pom = Path(__file__).resolve().parents[1] / 'G2A_Wapp/pom.xml'
    text = pom.read_text(encoding='utf-8')
    pattern = r'(?m)^    <version>(1\.[0-9]+\.[0-9]+)</version>$'
    found = list(re.finditer(pattern, text))
    if len(found) != 1:
        raise SystemExit('Expected one direct project version 1.Y.Z; no changes')
    current = found[0].group(1)
    new = next_version(current, args.kind)
    if not args.dry_run:
        start, end = found[0].span(1)
        pom.write_text(text[:start] + new + text[end:], encoding='utf-8')
    print(f'{current} -> {new}' + (' (dry run)' if args.dry_run else ''))


if __name__ == '__main__':
    main()
