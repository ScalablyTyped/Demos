import { configure } from '@storybook/react';

function loadStories() {
    require('../target/scala-2.12/storybook-react-opt.js');
}

configure(loadStories, module);
