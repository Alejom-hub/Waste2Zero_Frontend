import SwiftUI

struct ScannerView: View {
    let onLogout: () -> Void

    var body: some View {
        ZStack(alignment: .bottom) {
            VStack(spacing: 0) {
                HStack {
                    Button(action: onLogout) {
                        Image(systemName: "line.3.horizontal")
                            .font(.system(size: 18, weight: .bold))
                            .foregroundStyle(.white)
                            .frame(width: 40, height: 40)
                            .background(AppColors.greenWaste)
                            .clipShape(Circle())
                    }

                    Spacer()

                    Circle()
                        .fill(Color(red: 0.88, green: 0.76, blue: 0.69))
                        .frame(width: 40, height: 40)
                }
                .padding(.horizontal, 16)
                .padding(.top, 16)

                Text("Lee el codigo de barras del producto que quieras consultar")
                    .font(.system(size: 20, weight: .bold, design: .rounded))
                    .multilineTextAlignment(.center)
                    .foregroundStyle(.black)
                    .padding(.horizontal, 32)
                    .padding(.top, 24)

                ZStack {
                    RoundedRectangle(cornerRadius: 32)
                        .fill(Color(red: 0.85, green: 0.89, blue: 0.84))
                        .frame(width: 300, height: 300)

                    VStack(spacing: 20) {
                        Text("SCAN")
                            .font(.system(size: 32, weight: .black, design: .rounded))
                            .tracking(6)
                            .foregroundStyle(AppColors.darkGreenWaste)

                        Image(systemName: "qrcode.viewfinder")
                            .font(.system(size: 120, weight: .regular))
                            .foregroundStyle(AppColors.darkGreenWaste)
                    }
                }
                .padding(.top, 32)

                Image(systemName: "barcode")
                    .font(.system(size: 56, weight: .medium))
                    .foregroundStyle(.black)
                    .padding(.top, 40)

                Text("PRODUCTO: Tortillas-Bimbo")
                    .font(.system(size: 22, weight: .bold, design: .rounded))
                    .foregroundStyle(.black)
                    .padding(.top, 30)

                Spacer(minLength: 120)
            }

            BottomScannerBar()

            Button(action: {}) {
                Image(systemName: "plus")
                    .font(.system(size: 36, weight: .bold))
                    .foregroundStyle(.white)
                    .frame(width: 64, height: 64)
                    .background(AppColors.greenWaste)
                    .clipShape(Circle())
            }
            .offset(y: -57)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
        .ignoresSafeArea(edges: .bottom)
    }
}

private struct BottomScannerBar: View {
    var body: some View {
        HStack(spacing: 26) {
            navIcon("house", selected: false)
            navIcon("qrcode.viewfinder", selected: true)

            Color.clear
                .frame(width: 64, height: 1)

            navIcon("bell", selected: false)
            navIcon("heart", selected: false)
        }
        .frame(maxWidth: .infinity)
        .frame(height: 90)
        .background(
            RoundedRectangle(cornerRadius: 40)
                .fill(AppColors.ultraLightGreenWaste)
        )
    }

    private func navIcon(_ systemName: String, selected: Bool) -> some View {
        Image(systemName: systemName)
            .font(.system(size: 32, weight: .regular))
            .foregroundStyle(selected ? AppColors.greenWaste : AppColors.darkGreenWaste)
    }
}

#Preview {
    ScannerView(onLogout: {})
}
