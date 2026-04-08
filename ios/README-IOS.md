# Waste2Zero iOS

Este folder contiene una version iOS (SwiftUI) con el mismo flujo principal de Android:
- Login
- Navegacion a pantalla de scanner
- Boton de logout (regresa a login)

## 1) Generar proyecto Xcode

Recomendado con XcodeGen:

```bash
brew install xcodegen
cd ios
xcodegen generate
```

Esto crea `Waste2ZeroiOS.xcodeproj`.

## 2) Abrir y ejecutar en simulador

```bash
open Waste2ZeroiOS.xcodeproj
```

En Xcode:
1. Selecciona target `Waste2ZeroiOS`.
2. Elige un simulador iPhone.
3. Presiona Run.

## 3) Ejecutar por consola (opcional)

```bash
xcodebuild \
  -project Waste2ZeroiOS.xcodeproj \
  -scheme Waste2ZeroiOS \
  -destination 'platform=iOS Simulator,name=iPhone 16' \
  build
```

## Nota sobre cuenta Apple

Para correr en simulador no necesitas firma completa de Apple Developer.
Para dispositivo fisico, usa tu Team en Signing & Capabilities y cambia el bundle id si es necesario.
