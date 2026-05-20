# Design System Specification: The Kinetic Minimalist

## 1. Overview & Creative North Star
The "Kinetic Minimalist" is the creative foundation of this design system. In an industry of cluttered financial dashboards, this system moves in the opposite direction: **The Digital Curator.** 

The goal is to transform subscription management from a chore into a high-end editorial experience. We achieve this by breaking the rigid "grid-of-boxes" mentality common in Material 3. Instead, we use **intentional asymmetry**, **exaggerated whitespace**, and **tonal depth** to guide the eye. This system feels less like a utility and more like a premium concierge service—calm, authoritative, and vibrantly precise.

## 2. Color & Surface Architecture
We move beyond flat UI by treating the screen as a physical composition of layered materials. 

### The Palette (Material 3 Tokens)
*   **Surface Base:** `surface` (#f5f6f7) - Our "Off-White" canvas.
*   **Action Accent:** `primary` (#004be2) - A high-voltage electric blue for focus.
*   **Semantic Alert:** `error` (#b41340) - For cancellations or missed payments.
*   **Neutral Text:** `on_surface` (#2c2f30) - Deep charcoal, never pure black, for a softer optical ink.

### The "No-Line" Rule
**Borders are prohibited for sectioning.** To separate a subscription list from a summary card, you must use a background shift. 
*   Place a `surface_container_lowest` (#ffffff) card atop a `surface_container_low` (#eff1f2) background. 
*   The transition of light defines the boundary, not a 1px stroke. This creates a "seamless" editorial look.

### The Glass & Gradient Rule
To inject "soul" into the professional aesthetic:
*   **Signature Gradients:** For high-impact CTAs (e.g., "Add Subscription"), use a subtle linear gradient from `primary` (#004be2) to `primary_container` (#809bff) at a 135° angle.
*   **Glassmorphism:** Bottom navigation bars and floating headers must use `surface` at 85% opacity with a `20px` backdrop-blur. This allows content to bleed through, making the UI feel integrated and airy.

## 3. Typography: Editorial Authority
We utilize **Inter** for its mathematical precision and neutral tone, allowing the vibrant blue accents to take center stage.

*   **Display (lg/md):** Used for total monthly spend. Treat these as "Art." Use `display-lg` (3.5rem) with tight letter-spacing (-0.02em) to create a bold, confident statement.
*   **Headlines (sm/md):** For screen titles. These should sit in the top-left with ample `spacing-12` (4rem) of top padding to lean into the "Digital Curator" aesthetic.
*   **Labels (md/sm):** Reserved for metadata (e.g., "Next Billing Date"). Use `on_surface_variant` (#595c5d) to pull focus away from secondary info.
*   **Hierarchy Note:** Always pair a `headline-sm` with a `body-md`. The contrast in scale communicates professionalism more effectively than varying font weights.

## 4. Elevation & Depth
Depth in this system is a result of **Tonal Layering**, not structural shadows.

*   **The Layering Principle:** 
    1.  Base: `surface`
    2.  Section: `surface_container_low`
    3.  Interactive Card: `surface_container_lowest`
*   **Ambient Shadows:** For floating elements (Modals/FABs), use an "Extra-Diffused" shadow: `0px 20px 40px rgba(44, 47, 48, 0.06)`. By using the `on_surface` color for the shadow rather than black, the shadow feels like a natural atmospheric occlusion.
*   **Ghost Border Fallback:** Only where accessibility contrast ratios (WCAG 2.1) cannot be met through color shifts, use a 1px border of `outline_variant` (#abadae) at **15% opacity**.

## 5. Components & Interaction

### Cards & Lists (Subscription Items)
*   **Forbid Dividers:** Use `spacing-3` (1rem) of vertical gap between items rather than horizontal lines.
*   **The "Active State":** When a subscription card is pressed, it should not just change color; it should scale to `0.98` and transition to `surface_container_high`.

### Buttons
*   **Primary:** High-gloss `primary` (#004be2) with `on_primary` text. Use `rounded-lg` (0.5rem) for a modern, slightly softened corner.
*   **Secondary:** No background. Use a `ghost-border` with `primary` colored text. This keeps the interface light.

### Inputs (Subscription Search/Entry)
*   **Form Style:** Use "Filled" variants but replace the bottom stroke with a simple `surface_container_highest` background. 
*   **Focus State:** On focus, the background remains, but a 2px `primary` "glow" is applied via a soft inner-shadow, rather than a hard outer border.

### Chips (Category Filters: "Streaming", "Utilities")
*   **Design:** Use `rounded-full` (9999px).
*   **Color:** Unselected chips use `surface_container_high`. Selected chips use the electric `primary` with `on_primary` text.

### Specialized Component: The "Spend Meter"
Instead of a standard progress bar, use a thick `primary` line (8px) against a `surface_container_highest` track to visualize budget usage. The roundedness should be `full` to maintain the "clean" aesthetic.

## 6. Do’s and Don’ts

### Do
*   **Do** use `spacing-16` (5.5rem) or `20` (7rem) for hero section margins. Generous whitespace is the hallmark of premium design.
*   **Do** use asymmetrical layouts. For example, left-align your headlines but right-align your "Add" button to create a dynamic visual path.
*   **Do** use `primary_fixed` (#809bff) for subtle background tints in success states.

### Don’t
*   **Don’t** use a divider line, ever. If the layout feels "mushy," increase the contrast between your `surface` and `surface_container` tiers.
*   **Don’t** use pure black (#000000) for text. It breaks the "Off-White" softness of the system.
*   **Don’t** use standard Material 3 "Elevated" cards with heavy shadows. Use the "No-Line" layering rule instead.