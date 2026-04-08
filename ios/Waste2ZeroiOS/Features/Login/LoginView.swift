import SwiftUI

struct LoginView: View {
    let onLoginSuccess: () -> Void

    var body: some View {
        VStack(spacing: 0) {
            Spacer()

            VStack(spacing: 18) {
                Image(systemName: "leaf.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 90, height: 90)
                    .foregroundStyle(.white)

                Text("WASTE\nZERO")
                    .multilineTextAlignment(.center)
                    .font(.system(size: 56, weight: .black, design: .rounded))
                    .foregroundStyle(.white)
                    .lineSpacing(-12)
            }

            Spacer()

            VStack(spacing: 16) {
                Button(action: {
                    // Registration flow placeholder to mirror Android behavior.
                }) {
                    Text("Registrarse")
                        .font(.system(size: 24, weight: .bold, design: .rounded))
                        .frame(maxWidth: .infinity)
                        .frame(height: 64)
                        .foregroundStyle(.white)
                        .background(AppColors.lightGreenWaste)
                        .clipShape(Capsule())
                }

                Button(action: onLoginSuccess) {
                    Text("Iniciar Sesion")
                        .font(.system(size: 20, weight: .semibold, design: .rounded))
                        .foregroundStyle(.white)
                }
            }

            Spacer()
                .frame(height: 60)
        }
        .padding(.horizontal, 40)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(AppColors.greenWaste.ignoresSafeArea())
    }
}

#Preview {
    LoginView(onLoginSuccess: {})
}
