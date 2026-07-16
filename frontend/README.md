# Frontend Module - Angular

This is the Angular-based frontend module for the Modulith Shop application.

## Project Structure

```
frontend/
├── src/
│   ├── app/
│   │   ├── app.component.ts       # Root component
│   │   ├── app.component.html     # Component template
│   │   ├── app.component.scss     # Component styles
│   │   └── app.module.ts          # App module
│   ├── main.ts                    # Application entry point
│   ├── index.html                 # HTML template
│   └── styles.scss                # Global styles
├── dist/                          # Built output
├── package.json                   # NPM dependencies
├── angular.json                   # Angular configuration
├── tsconfig.json                  # TypeScript configuration
├── pom.xml                        # Maven configuration
└── README.md                      # This file
```

## Development

### Prerequisites

- Node.js 18+
- npm 9+
- Angular CLI 21+

### Installation

```bash
cd frontend
npm install --legacy-peer-deps
```

### Development Server

```bash
npm start
```

Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

### Build

```bash
npm run build
```

For production build:
```bash
npm run build:prod
```

The build artifacts will be stored in the `dist/frontend/` directory.

## Maven Integration

This module is integrated with the parent Maven project. To build the entire project including the frontend:

```bash
mvn clean install
```

The Maven build will:
1. Install Node.js and npm
2. Install npm dependencies
3. Build the Angular application
4. Package the built files

## Component Structure

### AppComponent

The main application component that displays a "Hello World" welcome message.

- **Template**: `src/app/app.component.html`
- **Styles**: `src/app/app.component.scss`
- **Logic**: `src/app/app.component.ts`

## Technologies

- Angular 21
- TypeScript 5.5
- SCSS/CSS
- RxJS
- Zone.js

## Testing

Testing infrastructure is set up but not yet implemented. Add tests as needed:

```bash
npm test
```
