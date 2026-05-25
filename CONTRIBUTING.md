# Contributing

Discuss changes in `#mapvina` on the
[OSM-US Slack](https://slack.openstreetmap.us/).

## Before Making Changes

See the
[development overview](https://mapvina.io/github/mapvina-native-ffi/development/)
for platform setup, pinned tools, local commands, tests, and examples.

Read [concepts](https://mapvina.io/github/mapvina-native-ffi/concepts/) before
changing behavior. Read the
[C API Conventions](https://mapvina.io/github/mapvina-native-ffi/development/c-conventions/)
before changing public C interfaces or C ABI behavior. Read the
[Binding Conventions](https://mapvina.io/github/mapvina-native-ffi/development/bindings/)
before changing language bindings or generated binding reference docs.

Keep pull requests focused on one reviewable change. The reviewer should be able
to connect the use case, public behavior, implementation, and validation without
separating unrelated work.

## Pull Requests

Open a pull request when the change is ready for review and include:

- the problem or use case;
- the public API or behavior change, if any;
- the validation you ran;
- platform limitations or native MapVina behavior you checked;

If you use AI assistance, review
[MapVina's AI Policy](https://github.io/github/mapvina/mapvina/blob/main/AI_POLICY.md),
verify generated content before requesting review, and disclose AI usage in the
pull request.
