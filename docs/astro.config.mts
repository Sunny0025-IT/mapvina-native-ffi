// @ts-check

import starlight from "@astrojs/starlight";
import { defineConfig } from "astro/config";
import starlightCopyButton from "starlight-copy-button";
import starlightLinksValidator from "starlight-links-validator";
import starlightLlmsTxt from "starlight-llms-txt";

// https://astro.build/config
export default defineConfig({
  site: "https://mapvina.com",
  base: "/mapvina-native-ffi",
  integrations: [
    starlight({
      title: "MapVina Native FFI",
      logo: {
        light: "./src/assets/mapvina-logo-square-for-light-bg.svg",
        dark: "./src/assets/mapvina-logo-square-for-dark-bg.svg",
      },
      editLink: {
        baseUrl:
          "https://github.com/mapvina/mapvina-native-ffi/edit/main/docs/",
      },
      customCss: ["./src/styles/custom.css"],
      plugins: [
        starlightCopyButton(),
        starlightLlmsTxt({ exclude: ["reference/**"] }),
        starlightLinksValidator(),
      ],
      social: [
        {
          icon: "github",
          label: "GitHub",
          href: "https://github.com/mapvina/mapvina-native-ffi",
        },
      ],
      sidebar: [
        { label: "Overview", link: "/" },
        { label: "Concepts", slug: "concepts" },
        {
          label: "Usage",
          autogenerate: { directory: "guides" },
        },
        {
          label: "Reference",
          autogenerate: { directory: "reference" },
        },
        {
          label: "Development",
          autogenerate: { directory: "development" },
        },
      ],
    }),
  ],
});
